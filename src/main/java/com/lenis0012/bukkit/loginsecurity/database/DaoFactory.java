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

package com.lenis0012.bukkit.loginsecurity.database;

public interface DaoFactory {

    /**
     * Get profile data access object.
     * It handles profile storage.
     *
     * @return Profile DAO
     */
    ProfileDao getProfileDao();

    /**
     * Get the location data access object.
     * It handles location storage.
     *
     * @return Location DAO
     */
    LocationDao getLocationDao();

    /**
     * Get the inventory data access object.
     * It handles inventory storage.
     *
     * @return Inventory DAO
     */
    InventoryDao getInventoryDao();
}
