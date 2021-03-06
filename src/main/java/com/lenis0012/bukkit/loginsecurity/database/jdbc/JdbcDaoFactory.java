/*
 * This file is a part of LoginSecurity.
 *
 * Copyright (c) 2017 Lennart ten Wolde
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lenis0012.bukkit.loginsecurity.database.jdbc;

import com.lenis0012.bukkit.loginsecurity.database.DaoFactory;
import com.lenis0012.bukkit.loginsecurity.database.InventoryDao;
import com.lenis0012.bukkit.loginsecurity.database.LocationDao;
import com.lenis0012.bukkit.loginsecurity.database.ProfileDao;
import com.lenis0012.bukkit.loginsecurity.database.jdbc.platform.JdbcPlatform;
import org.bukkit.configuration.ConfigurationSection;

import java.util.logging.Logger;

public class JdbcDaoFactory implements DaoFactory {
    private final Logger logger;
    private final JdbcConnectionPool connectionPool;
    private final String platformName;

    private JdbcProfileDao profileDao;
    private JdbcMigrationDao migrationDao;
    JdbcLocationDao locationDao;
    JdbcInventoryDao inventoryDao;

    public JdbcDaoFactory(Logger logger, JdbcConnectionPool connectionPool, String platformName) {
        this.logger = logger;
        this.connectionPool = connectionPool;
        this.platformName = platformName;
    }

    @Override
    public ProfileDao getProfileDao() {
        if(profileDao == null) {
            this.profileDao = new JdbcProfileDao(this, connectionPool, logger);
        }
        return profileDao;
    }

    @Override
    public LocationDao getLocationDao() {
        if(locationDao == null) {
            this.locationDao = new JdbcLocationDao(connectionPool, logger);
        }
        return locationDao;
    }

    @Override
    public InventoryDao getInventoryDao() {
        if(inventoryDao == null) {
            inventoryDao = new JdbcInventoryDao(connectionPool, logger);
        }
        return inventoryDao;
    }

    public JdbcMigrationDao getMigrationDao() {
        if(migrationDao == null) {
            this.migrationDao = new JdbcMigrationDao(connectionPool, logger);
        }
        return migrationDao;
    }

    public String getPlatformName() {
        return platformName;
    }

    public static JdbcDaoFactory build(Logger logger, ConfigurationSection configuration, JdbcPlatform platform) {
        JdbcConnectionPool connectionPool = new JdbcConnectionPool(platform.configure(configuration), platform.getPingTimeout(configuration));
        return new JdbcDaoFactory(logger, connectionPool, configuration.getName());
    }
}
