/**
 * See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * con terra GmbH licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.conterra.smarteditor.validator;

import java.io.StringWriter;

import de.conterra.smarteditor.beans.BackendBean;
import de.conterra.smarteditor.service.BackendManagerService;
import de.conterra.smarteditor.util.DOMUtil;
import de.conterra.smarteditor.util.XPathUtil;
import de.conterra.smarteditor.xml.EditorContext;

import org.apache.log4j.Logger;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;

/**
 * Main validator class for validation
 * <p/>
 * 
 * @author kse Date: 27.02.2010 Time: 18:05:46
 */
public class MetadataValidator implements Validator {

	private BackendManagerService mService;
	static private Logger LOG = Logger.getLogger(MetadataValidator.class);

	public BackendManagerService getService() {
		return mService;
	}

	public void setService(BackendManagerService pService) {
		mService = pService;
	}

	public boolean supports(Class clazz) {
		return BackendBean.class.isAssignableFrom(clazz);
	}

	/**
	 * validates the backend bean properties against a given schematron (or
	 * else) validator
	 *
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {
		BackendBean lBean = (BackendBean) target;
		// chekc if we need to validate
		if (lBean.getValidatorId() != null
				&& !lBean.getValidatorId().equals("")) {
			// apply schematron transformation
			Document lReport = mService.validate(lBean.getValidatorId());
			LOG.debug("lReport from validation: "+DOMUtil.convertToString(lReport, true));
			// add assertions to errors.
			XPathUtil lUtil = new XPathUtil();
			lUtil.setContext(new EditorContext());
			Object o = lUtil.evaluateXPath("//svrl:failed-assert",
					XPathConstants.NODESET, lReport);
			if (o != null) {
				NodeList lList = ((NodeList) o);
				if (lList.getLength() > 0) {
					LOG.debug("There are validation errors: "
							+ lList.toString());
				} else {
					LOG.debug("There are no validation errors");
				}
				for (int i = 0; i < lList.getLength(); i++) {
					Node lNode = lList.item(i);
					LOG.debug("error: " + lList.item(i));
					errors.rejectValue(lUtil.evaluateAsString("@id", lNode),
							lUtil.evaluateAsString("svrl:text", lNode));
				}
			}
		}
	}
}
