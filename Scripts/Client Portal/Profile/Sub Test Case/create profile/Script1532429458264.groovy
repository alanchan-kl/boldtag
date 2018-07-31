import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.awt.RenderingHints.Key

import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory as CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as MobileBuiltInKeywords
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.TestCaseFactory as TestCaseFactory
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testdata.TestDataFactory as TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WSBuiltInKeywords
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable

if (GlobalVariable.loginAction == false) {
    WebUI.callTestCase(findTestCase('Client Portal/Login/Verify Login Success'), [('username') : GlobalVariable.username
            , ('password') : GlobalVariable.password], FailureHandling.STOP_ON_FAILURE)
}

if (GlobalVariable.moduleAccess == false) {
	WebUI.callTestCase(findTestCase('Client Portal/Module Access/Acess Profile Screen'), [:], FailureHandling.STOP_ON_FAILURE)
	if(updateAction.toLowerCase() == 'add'){
		WebUI.waitForElementVisible(findTestObject('Client Portal/Profile/header_profile'), 2)
		
		WebUI.waitForElementVisible(findTestObject('Client Portal/Profile/button_New Profile'), 2)
		
		WebUI.click(findTestObject('Client Portal/Profile/button_New Profile'))
	}
}

WebUI.delay(2)
WebUI.waitForElementPresent(findTestObject('Client Portal/Profile/Create Profile/label_Profile Name'), 2)
WebUI.waitForElementVisible(findTestObject('Client Portal/Profile/Create Profile/label_Profile Name'), 2)

WebUI.setText(findTestObject('Client Portal/Profile/Create Profile/Fields and Actions/input_profile name'), profileName)

if(updateAction.toLowerCase() == 'add'){
	WebUI.click(findTestObject('Client Portal/Profile/Create Profile/Fields and Actions/button_Create'))
}else if (updateAction.toLowerCase() == 'edit'){
	WebUI.click(findTestObject('Object Repository/Client Portal/Profile/Edit Profile/button_Save'))
}



