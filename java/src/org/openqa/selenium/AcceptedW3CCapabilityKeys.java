// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium;

import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class AcceptedW3CCapabilityKeys implements Predicate<String> {

  private static final Predicate<String> ACCEPTED_W3C_PATTERNS = Stream.of(
      "^[\\w-]+:.*$",
      "^acceptInsecureCerts$",
      "^browserName$",
      "^browserVersion$",
      "^platformName$",
      "^pageLoadStrategy$",
      "^proxy$",
      "^setWindowRect$",
      "^strictFileInteractability$",
      // TODO: Needs to be removed when ChromeDriver issue is resolved. Not a W3C capability
      //  https://bugs.chromium.org/p/chromedriver/issues/detail?id=4129
      "^networkConnectionEnabled$",
      // TODO: Needs to be removed when ChromeDriver issue is resolved. Not a W3C capability
      // https://bugs.chromium.org/p/chromedriver/issues/detail?id=4129
      "^chrome$",
      // TODO: Needs to be removed when MSEdgeDriver issue is resolved. Not a W3C capability
      // https://github.com/MicrosoftEdge/EdgeWebDriver/issues/23
      "^msedge$",
      // TODO: Needs to be removed when GeckoDriver issue is resolved. Not a W3C capability
      // https://github.com/mozilla/geckodriver/issues/2023
      "^platformVersion$",
      "^timeouts$",
      "^unhandledPromptBehavior$",
      "^webSocketUrl$")  // from webdriver-bidi
    .map(Pattern::compile)
    .map(Pattern::asPredicate)
    .reduce(identity -> false, Predicate::or);

  @Override
  public boolean test(String capabilityName) {
    return ACCEPTED_W3C_PATTERNS.test(capabilityName);
  }
}
