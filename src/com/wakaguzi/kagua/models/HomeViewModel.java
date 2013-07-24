package com.wakaguzi.kagua.models;

public class HomeViewModel {

	String _vm_name;
	String _vm_phone;
	String _vm_lastvisit;
	String _vm_image_url;

	public HomeViewModel() {

	}

	public HomeViewModel(String name, String phone, String last_visit,String image_url) {		

		_vm_name = name;
		_vm_phone = phone;
		_vm_lastvisit = last_visit;
		_vm_image_url = image_url;
	}

	public String getvm_name() {
		return this._vm_name;
	}

	public String getvm_phone() {
		return this._vm_phone;
	}

	public String getvm_lastvisit() {
		return this._vm_lastvisit;
	}

	public String getvm_image_url() {
		return this._vm_image_url;
	}
}
