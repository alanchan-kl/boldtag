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
import com.kms.katalon.core.exception.StepErrorException as StepErrorException

WebUI.waitForElementVisible(findTestObject('Client Portal/a.Common/popout_msg'), 2)

def serverMsg = WebUI.getText(findTestObject('Client Portal/a.Common/popout_msg'))

def updateSuccess = false
def updateFailed = false

if (WebUI.verifyMatch(serverMsg, msgCreateSuccess, true, FailureHandling.OPTIONAL)) {
	updateSuccess = true
} 

if (WebUI.verifyMatch(serverMsg, msgEditSuccess, true, FailureHandling.OPTIONAL)) {
	updateSuccess = true
}

if(updateSuccess == true){
	WebUI.callTestCase(findTestCase('Client Portal/Profile/Sub Test Case/search profile'), [('profileName') : profileName, ('filterSearch') : filterSearch], FailureHandling.STOP_ON_FAILURE)
	GlobalVariable.moduleAccess = false
}

if(WebUI.waitForElementVisible(findTestObject('Client Portal/Profile/Error Msg/msg_duplicate'), 2)){
	updateFailed = true
	WebUI.delay(2)
	WebUI.refresh()
}

if(WebUI.waitForElementVisible(findTestObject('Client Portal/Profile/Error Msg/msg_mandatory'), 2)){
	updateFailed = true
	WebUI.delay(2)
	WebUI.refresh()
	
}

if(updateSuccess == false && updateFailed == false){
	throw new com.kms.katalon.core.exception.StepErrorException('Ops. There is proble encountered.')
}


