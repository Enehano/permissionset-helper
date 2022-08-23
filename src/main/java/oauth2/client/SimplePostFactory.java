/*
 * Copyright (c) 2015, salesforce.com, inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided
 * that the following conditions are met:
 *
 *    Redistributions of source code must retain the above copyright notice, this list of conditions and the
 *    following disclaimer.
 *
 *    Redistributions in binary form must reproduce the above copyright notice, this list of conditions and
 *    the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 *    Neither the name of salesforce.com, inc. nor the names of its contributors may be used to endorse or
 *    promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package oauth2.client;

import org.apache.http.message.BasicNameValuePair;
import utils.Config;

import java.util.function.Function;

/**
 * Created by rmazzeo on 12/9/15.
 */
public class SimplePostFactory {

    private static Function<Criteria, SimplePost> constructor = c -> new DefaultSimplePost(c.config, c.endpoint, c.pairs);;

    public static class Criteria{
        public Config config;
        public String endpoint;
        public BasicNameValuePair[] pairs;
    }

    public static Function<Criteria, SimplePost> getConstructor()
    {
        return constructor;
    }

    public static void setConstructor(Function<Criteria, SimplePost> constructor)
    {
        SimplePostFactory.constructor = constructor;
    }

    public static SimplePost getInstance(Config config, String endpoint, BasicNameValuePair... pairs){
        Criteria criteria = new Criteria();
        criteria.config = config;
        criteria.endpoint = endpoint;
        criteria.pairs = pairs;

        return constructor.apply(criteria);

    }
}
