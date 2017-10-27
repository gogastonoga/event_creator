package com.capgemini.wolimierz.controller;

public class BaseController {
    protected static final String ADMIN = "hasAuthority('ADMIN')";
    protected static final String ADMIN_OR_STAFF = "hasAuthority('ADMIN') or hasAuthority('STAFF')";
}
