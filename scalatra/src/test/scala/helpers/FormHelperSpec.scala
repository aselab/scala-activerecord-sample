package helpers

import org.specs2.mutable._

object FormHelperSpec extends Specification {
  "FormHelper" should {
    import BaseFormHelper._

    "extendXml" in {
      "attr(k, v)" in {
        <div/> attr ("class", "hoge") must ==/(<div class="hoge"></div>)
      }

      "attr((k, v)*)" in {
        <div/> attr (("class", "hoge"), ("id", "piyo")) must ==/(<div class="hoge" id="piyo"></div>)
      }
    }

    "div" in {
      div must ==/(<div/>)
    }

    "div {}" in {
      div {
        <b>bbbb</b>
      } must ==/(<div><b>bbbb</b></div>)
    }

    "div('str')" in {
      div("str") must ==/(<div>str</div>)
    }

    "div {}" in {
      div {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<div><b>bbbb</b><b>bbbb</b></div>)
    }

    "div {}" in {
      div {
        <input/>
        <input/>
      } must ==/(<div><input/><input/></div>)
    }

    "input" in {
      input must ==/(<input/>)
    }

    "ol {}" in {
      ol {
        <li>aaaa</li>
        <li>bbbb</li>
      } must ==/(<ol><li>aaaa</li><li>bbbb</li></ol>)
    }

    "li {}" in {
      li {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<li><b>bbbb</b><b>bbbb</b></li>)
    }

    "li('str')" in {
      li("str") must ==/(<li>str</li>)
    }

    "span {}" in {
      span {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<span><b>bbbb</b><b>bbbb</b></span>)
    }

    "span('str')" in {
      li("str") must ==/(<li>str</li>)
    }

    "p {}" in {
      BaseFormHelper.p {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<p><b>bbbb</b><b>bbbb</b></p>)
    }

    "p('str')" in {
      BaseFormHelper.p("str") must ==/(<p>str</p>)
    }

    "label {}" in {
      label {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<label><b>bbbb</b><b>bbbb</b></label>)
    }

    "label('str')" in {
      label("str") must ==/(<label>str</label>)
    }

    "fieldset {}" in {
      fieldset {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<fieldset><b>bbbb</b><b>bbbb</b></fieldset>)
    }

    "form {}" in {
      form {
        <b>bbbb</b>
        <b>bbbb</b>
      } must ==/(<form><b>bbbb</b><b>bbbb</b></form>)
    }

    "select {}" in {
      select {
        <option>bbbb</option>
        <option>bbbb</option>
      } must ==/(<select><option>bbbb</option><option>bbbb</option></select>)
    }

    "option('str')" in {
      option("str") must ==/(<option>str</option>)
    }
  }
}
