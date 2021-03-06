/*
 * Copyright 2019 BotsCrew
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

package com.botscrew.botframework.plivo.service.impl;

import com.botscrew.botframework.plivo.config.property.PlivoProperties;
import com.botscrew.botframework.plivo.model.PlivoMessage;
import com.botscrew.botframework.plivo.model.PlivoUser;
import com.botscrew.botframework.plivo.service.PlivoSender;
import com.botscrew.botframework.plivo.service.PlivoTokenizedSender;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ScheduledFuture;

@RequiredArgsConstructor
public class DefaultPlivoSender implements PlivoSender {

    private final PlivoTokenizedSender plivoTokenizedSender;
    private final PlivoProperties plivoProperties;

    @Override
    public void send(PlivoMessage plivoMessage) {
        send(plivoMessage.getUser(), plivoMessage.getText());
    }

    @Override
    public void send(PlivoUser plivoUser, String text) {
        plivoTokenizedSender.send(plivoProperties.getAuthId(), plivoProperties.getAuthToken(),
                plivoProperties.getPhoneNumber(), plivoUser, text);
    }

    @Override
    public ScheduledFuture send(PlivoUser plivoUser, String text, int delay) {
        return plivoTokenizedSender.send(plivoProperties.getAuthId(), plivoProperties.getAuthToken(),
                plivoProperties.getPhoneNumber(), plivoUser, text, delay);
    }
}
