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

package com.lenis0012.bukkit.loginsecurity.database.jdbc.platform;

import com.lenis0012.bukkit.loginsecurity.LoginSecurity;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.sqlite.javax.SQLiteConnectionPoolDataSource;

import javax.sql.ConnectionPoolDataSource;
import java.io.File;

public class SqlitePlatform implements JdbcPlatform {

    @Override
    public ConnectionPoolDataSource configure(ConfigurationSection configuration) {
        SQLiteConnectionPoolDataSource dataSource = new SQLiteConnectionPoolDataSource();

        String path = new File(LoginSecurity.getInstance().getDataFolder(), "LoginSecurity.db").getPath();
        dataSource.setDatabaseName(path.replace(File.separator, "/"));

        return dataSource;
    }

    @Override
    public int getPingTimeout(ConfigurationSection configuration) {
        return configuration.getInt("ping-timeout", 10);
    }
}
