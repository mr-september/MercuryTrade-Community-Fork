package com.mercury.platform.ui.components.panel.notification.controller;

public interface ScannerPanelController extends NotificationController {
    void visitHideout();

    void performInvite();

    void performLeave();

    void performWhoIs();
    
    void performGlobalChatResponse(String plusText);
}
