/**
 * Copyright 2017 Lending Club, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.macgyver.plugin.hipchat;

import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.JsonNode;

import io.macgyver.core.Kernel;
import io.macgyver.core.service.ServiceDefinition;
import io.macgyver.core.service.ServiceRegistry;
import io.macgyver.plugin.chat.ChatBot;
import io.macgyver.plugin.chat.ChatBotCommand;
import io.macgyver.plugin.chat.ChatBotContext;

public class HipChatBot extends ChatBot {

	

	@Override
	public ChatBotContext createContext(ChatBot bot, JsonNode n) {
		HipChatBotContext ctx = new HipChatBotContext(this, n);
		
		return ctx;
	}

	public HipChatClient getHipChatClient() {
		ServiceRegistry registry = Kernel.getApplicationContext().getBean(ServiceRegistry.class);
		
		for (Map.Entry<String,ServiceDefinition> entry: Kernel.getApplicationContext().getBean(ServiceRegistry.class).getServiceDefinitions().entrySet()) {
			
			ServiceDefinition def = entry.getValue();
			if (def.getServiceFactory() instanceof HipChatServiceFactory) {
				return (HipChatClient) registry.get(def.getPrimaryName());
			}
		}
		
		throw new IllegalStateException("could not resolve a hipchat service");
	}
	
}
