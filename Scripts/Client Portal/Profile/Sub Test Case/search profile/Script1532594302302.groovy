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
import org.openqa.selenium.By as By
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def verifyRecord = false
def dateCreated = ''
//throw new com.kms.katalon.core.exception.StepErrorException('')

WebDriver driver = DriverFactory.getWebDriver()
'To locate table'
WebElement Table = driver.findElement(By.xpath("//table/tbody"))
'To locate rows of table it will Capture all the rows available in the table'
List<WebElement> rows_table = Table.findElements(By.tagName('tr'))
'To calculate no of rows In table'
rows_count = rows_table.size()

'Loop will execute for all the rows of the table'
Loop:
for (row = 0; row < rows_count; row++) {
	'To locate columns(cells) of that specific row'
	List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName('td'))
	
	'To calculate no of columns(cells) In that specific row'
	columns_count = Columns_row.size()
	//println((('Number of cells In Row ' + row) + ' are ') + columns_count)
	
	'Loop will execute till the last cell of that specific row'
	for (int column = 0; column < columns_count; column++) {
		'It will retrieve text from each cell'
		def celltext = Columns_row.get(column).getText()
		 
		//println((((('Cell Value Of row number ' + row) + ' and column number ') + column) + ' Is ') + celltext)
		
		'Checking if Cell text is matching with the expected value'
		if (celltext == profileName) {
			'Getting the Date Created if cell text i.e Profile Name matches with Expected value'
			//println('Text present in row number 3 is: ' + Columns_row.get(2).getText())
			 
			dateCreated = Columns_row.get(2).getText()
			verifyRecord = true
			//WebUI.verifyMatch(dateCreated, dateCreated, true)
			
			if(searchAction.toLowerCase() == 'edit'){
				Columns_row.get(3).findElement(By.tagName('button')).click()
			}
			
			'After getting the Expected value from Table we will Terminate the loop'
			break Loop;
		}
	}
}

println(dateCreated)

if(verifyRecord == false){
	throw new com.kms.katalon.core.exception.StepErrorException('Unable to find record.')
}