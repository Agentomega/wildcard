package omega.wildcard.common;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = WildcardConstants.MODID, name = WildcardConstants.NAME, version = WildcardConstants.VERSION)
public class Wildcard {
	private static final String NAME = WildcardConstants.NAME;
	private static final String VERSION = WildcardConstants.VERSION;
	
	private static Logger logger;
	
	private static boolean appliedEnergisticsLoaded;
	private static boolean botaniaLoaded;
	private static boolean enderIOLoaded;
	private static boolean refinedStorageLoaded;
	private static boolean thermalDynamicsLoaded;
	
	@EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        
        botaniaLoaded = Loader.isModLoaded("botania");
    }
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
        logger.info("Initializing " + NAME + " version " + VERSION);
    }
}
