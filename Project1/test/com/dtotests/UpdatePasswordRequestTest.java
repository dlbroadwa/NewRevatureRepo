package com.dtotests;

import org.junit.Assert;
import org.junit.Test;

import gradebook.dto.UpdatePasswordRequest;

public class UpdatePasswordRequestTest {
	UpdatePasswordRequest newPassword = new UpdatePasswordRequest();
	
	@Test
	public void verifyOldPassword(){
		//ask the UpdatePasswordRequest to set and get a oldPassword value
		//assert that the correct value is returned
		newPassword.setOldPassword("oldPassword");
		String actual = newPassword.getOldPassword();
		Assert.assertEquals("Did not return expected value", "oldPassword", actual);
	}
	
	@Test
	public void verifyOldNewPassword1(){
		//ask the UpdatePasswordRequest to set and get a newPassword1 value
		//assert that the correct value is returned
		newPassword.setNewPassword1("newPasswordCopy1");
		String actual = newPassword.getNewPassword1();
		Assert.assertEquals("Did not return expected value", "newPasswordCopy1", actual);
	}
	
	@Test
	public void verifyOldNewPassword2(){
		//ask the UpdatePasswordRequest to set and get a newPassword2 value
		//assert that the correct value is returned
		newPassword.setNewPassword2("newPasswordCopy2");
		String actual = newPassword.getNewPassword2();
		Assert.assertEquals("Did not return expected value", "newPasswordCopy2", actual);
	}
}
