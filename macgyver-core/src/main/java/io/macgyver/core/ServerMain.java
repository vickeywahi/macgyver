package io.macgyver.core;

import io.macgyver.config.CoreConfig;
import io.macgyver.core.mapdb.BootstrapMapDB;

import org.mapdb.DB;
import org.mapdb.TxBlock;
import org.mapdb.TxRollbackException;
import org.slf4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Simple wrapper to start server.
 * 
 * @author rschoening
 * 
 */

@Configuration
@ComponentScan(basePackageClasses = { CoreConfig.class })
@EnableAutoConfiguration
public class ServerMain {

	static Logger logger = org.slf4j.LoggerFactory.getLogger(ServerMain.class);

	public static void main(String[] args) throws Exception {

		BootstrapMapDB bootstrap = BootstrapMapDB.getInstance();
		bootstrap.init();

		TxBlock b = new TxBlock() {

			@Override
			public void tx(DB db) throws TxRollbackException {

			}
		};
		bootstrap.getTxMaker().execute(b);

		ConfigurableApplicationContext ctx = SpringApplication.run(
				ServerMain.class, args);
		Environment env = ctx.getEnvironment();

		logger.info("Spring environment: {}", env);

	}
}
