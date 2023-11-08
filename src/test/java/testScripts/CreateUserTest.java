package testScripts;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateUserTest extends BaseClass{
	
	@Test
	public void createUserTest() {
		SoftAssert soft = new SoftAssert();
		home.clickUsersTab();
		soft.assertTrue(users.getPageHeader().contains("Users"));
		
		users.clickNewButton();
		soft.assertEquals(addUser.getPageHeader(), "Add New User");
		Map<String, String> map = excel.readFromExcel("Sheet2", "Add User");
		addUser.createNewUser(map.get("Email"), map.get("Password"),
				map.get("Firstname"), map.get("Lastname"), map.get("Address"),
				map.get("Contact Info"), map.get("Photo"));
		
		soft.assertTrue(users.getSuccessMessage().contains("Success"));
		
		if(course.getSuccessMessage().contains("Success"))
			excel.writeToExcel("Sheet2", "Add User", "pass", IConstantPath.EXCEL_PATH);
		else
			excel.writeToExcel("Sheet2", "Add User", "Fail", IConstantPath.EXCEL_PATH);
		
		soft.assertAll();
			
		}
		
	}


