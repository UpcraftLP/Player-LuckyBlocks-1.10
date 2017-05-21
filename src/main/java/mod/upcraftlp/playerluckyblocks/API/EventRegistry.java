package mod.upcraftlp.playerluckyblocks.API;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import mod.upcraftlp.playerluckyblocks.Main;
import mod.upcraftlp.playerluckyblocks.baseevents.EventItemDrop;
import mod.upcraftlp.playerluckyblocks.config.LuckyConfig;
import mod.upcraftlp.playerluckyblocks.util.Utils;
import net.minecraft.item.ItemStack;

public class EventRegistry {

	private static List<IEventProvider> positive = new ArrayList<>();
	private static List<IEventProvider> negative = new ArrayList<>();
	private static List<IEventProvider> neutral = new ArrayList<>();
	private static List<IEventProvider> badass = new ArrayList<>();
	
	public static void registerEvent(IEventProvider event, EnumLuck luck) {
		getListFromLuck(luck).add(event);
	}
	
	public static void registerDrop(List<ItemStack> drops, String dropName, EnumLuck luck) {
		getListFromLuck(luck).add(new EventItemDrop(drops, dropName));
	}
	
	private static List<IEventProvider> getListFromLuck(EnumLuck luck) {
		switch(luck) {
		case POSITIVE: return positive;
		case NEGATIVE: return negative;
		case BADASS: return badass;
		case NEUTRAL: 
		default: return neutral;
		}
		
	}
	
	@Nullable
	public static IEventProvider getEvent(EnumLuck luck) {
	    if(luck == EnumLuck.BADASS && LuckyConfig.pussyMode) luck = EnumLuck.NEGATIVE;
		List<IEventProvider> list = getListFromLuck(luck);
		if(list.isEmpty()) return null;
		return list.get(Utils.RANDOM.nextInt(list.size()));
	}
	
	/**
	 * removes ALL occurrences of a specific event
	 * @param eventName name of the event to remove
	 */
	public void unregister(String eventName) {
		for(EnumLuck luck : EnumLuck.values()) {
			List<IEventProvider> providers = getListFromLuck(luck);
			for (IEventProvider event : providers) {
				if (event.getName().equals(eventName)) {
					providers.remove(event);
					Main.getLogger().info("Successfully unregistered Event: " + eventName);
				}
			}
		}
	}
}
