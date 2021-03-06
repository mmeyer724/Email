/*
    This file is part of Email.

    Email is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Email is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Email.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.mike724.email;

import java.util.HashMap;

@SuppressWarnings("unused")
public class EmailProvider {
    private String key;
    private HashMap<String, String> props;

    public EmailProvider(String key, HashMap<String, String> props) {
        this.key = key;
        this.props = props;
    }

    public HashMap<String, String> getProps() {
        return props;
    }

    public void setProps(HashMap<String, String> props) {
        this.props = props;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}