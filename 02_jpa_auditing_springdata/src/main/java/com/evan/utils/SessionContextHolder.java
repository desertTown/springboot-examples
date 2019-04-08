package com.evan.utils;


import com.evan.config.StaffAuditor;

public class SessionContextHolder {

    //这里正确的做法是不应该赋值， 正确赋值操作应该在filter那里调用
    private static ThreadLocal<StaffAuditor> local = ThreadLocal.withInitial(() -> {
        StaffAuditor staffAuditor = new StaffAuditor();
        staffAuditor.setCreatedByDomainId("Fake created domainId");
        staffAuditor.setCreatedByGsid("Fake created gsid");
        staffAuditor.setUpdatedByDomainId("Fake udpated domainId");
        staffAuditor.setUpdatedByGsid("Fake updated gsid");
        return staffAuditor;
    });

    public static void set(StaffAuditor staffAuditor) {
        local.set(staffAuditor);
    }

    public static StaffAuditor get() {
        return local.get();
    }

    public static void remove(){
        local.remove();
    }
}
