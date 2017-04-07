package mod.upcraftlp.playerluckyblocks.proxy;

import mod.upcraftlp.playerluckyblocks.init.LuckyBlocks;
import mod.upcraftlp.playerluckyblocks.init.LuckyTabs;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		LuckyBlocks.registerSpecialRenders();
		LuckyTabs.setIcons();
		//FIXME Check for offline player and enable troll events!
		/**mcmod.info**/
		ModMetadata data = event.getModMetadata();
		data.autogenerated = false;
		//TODO: metadata!
	}
	
	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}
}
