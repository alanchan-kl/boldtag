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
import randomWords.GenerateAction as GenerateAction

if (GlobalVariable.loginAction == false) {
    WebUI.callTestCase(findTestCase('Client Portal/Login/Verify Login Success'), [('username') : GlobalVariable.username
            , ('password') : GlobalVariable.password], FailureHandling.STOP_ON_FAILURE)
}

if (GlobalVariable.moduleAccess == false) {
    WebUI.callTestCase(findTestCase('Client Portal/Module Access/Acess Profile Screen'), [:], FailureHandling.STOP_ON_FAILURE)
}

WebUI.setText(findTestObject('Client Portal/Profile/search_profileName'), profileName)

WebUI.click(findTestObject('Client Portal/Profile/search_profileName'))

WebUI.verifyElementNotPresent(findTestObject('Client Portal/Profile/icon_refresh'), 5)

WebUI.callTestCase(findTestCase('Client Portal/Profile/Sub Test Case/search profile'), [('searchAction') : searchAction, ('profileName') : profileName], 
    FailureHandling.STOP_ON_FAILURE)

def randomWords = CustomKeywords.'randomWords.GenerateAction.randomString'(5)

newprofileName = newprofileName + randomWords

WebUI.callTestCase(findTestCase('Client Portal/Profile/Sub Test Case/create profile'), [('profileName') : newprofileName, ('updateAction') : updateAction], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Client Portal/a.Common/popout_msg'), 2)

def serverMsg = WebUI.getText(findTestObject('Client Portal/a.Common/popout_msg'))

def updateSuccess = false

if (WebUI.verifyMatch(serverMsg, msgSuccess, true, FailureHandling.OPTIONAL)) {
	updateSuccess = true
} else {
	updateSuccess = false
}

if (updateSuccess == true) {
	WebUI.setText(findTestObject('Client Portal/Profile/search_profileName'), newprofileName)
	
	WebUI.click(findTestObject('Client Portal/Profile/search_profileName'))

	WebUI.waitForElementNotVisible(findTestObject('Client Portal/a.Common/popout_msg'), 5)

	WebUI.verifyElementNotPresent(findTestObject('Client Portal/Profile/icon_refresh'), 5)
	
	WebUI.callTestCase(findTestCase('Client Portal/Profile/Sub Test Case/search profile'), [('profileName') : newprofileName], FailureHandling.STOP_ON_FAILURE)
}


