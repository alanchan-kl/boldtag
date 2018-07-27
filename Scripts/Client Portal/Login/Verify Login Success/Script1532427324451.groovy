import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
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
import com.kms.katalon.core.exception.StepErrorException

WebUI.callTestCase(findTestCase('Client Portal/Login/Sub Test Case/Login Action'), [('username') : username, ('password') : password], 
    FailureHandling.STOP_ON_FAILURE)

def userAccess = false

if(WebUI.waitForElementVisible(findTestObject('Client Portal/a.Common/button_End tour'), 5)){
	WebUI.click(findTestObject('Client Portal/a.Common/button_End tour'))
	WebUI.waitForElementNotPresent(findTestObject('Client Portal/a.Common/button_End tour'), 5)
}

if(WebUI.waitForElementVisible(findTestObject('Client Portal/a.Common/sidebar_username'), 5)){
	def verifyusername1 = WebUI.getText(findTestObject('Client Portal/a.Common/sidebar_username'))
	WebUI.verifyMatch(verifyusername1, username, true)
	userAccess = true
}else {
	userAccess = false
}

if(WebUI.waitForElementVisible(findTestObject('Client Portal/a.Common/topbar_username'), 5)){
	def verifyusername2 = WebUI.getText(findTestObject('Client Portal/a.Common/topbar_username'))
	WebUI.verifyMatch(verifyusername2, username, true)
	userAccess = true
}else {
	userAccess = false
}

if(userAccess == false){
	throw new com.kms.katalon.core.exception.StepErrorException('Invalid username and password')
}



