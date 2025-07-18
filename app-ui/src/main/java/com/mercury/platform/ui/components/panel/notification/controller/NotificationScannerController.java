package com.mercury.platform.ui.components.panel.notification.controller;


import com.mercury.platform.shared.entity.message.PlainMessageDescriptor;
import com.mercury.platform.shared.store.MercuryStoreCore;

import javax.swing.*;

public class NotificationScannerController implements ScannerPanelController {
    private PlainMessageDescriptor notificationDescriptor;

    public NotificationScannerController(PlainMessageDescriptor notificationDescriptor) {
        this.notificationDescriptor = notificationDescriptor;
    }

    @Override
    public void visitHideout() {
        MercuryStoreCore.chatCommandSubject.onNext("/hideout " + notificationDescriptor.getNickName());
    }

    @Override
    public void performInvite() {
        MercuryStoreCore.chatCommandSubject.onNext("/invite " + notificationDescriptor.getNickName());
    }

    @Override
    public void performLeave() {
        MercuryStoreCore.chatCommandSubject.onNext("/leave");
    }

    @Override
    public void performWhoIs() {
        MercuryStoreCore.chatCommandSubject.onNext("/whois " + notificationDescriptor.getNickName());
    }

    @Override
    public void performOfferTrade() {
        MercuryStoreCore.chatCommandSubject.onNext("/tradewith " + notificationDescriptor.getNickName());
    }

    @Override
    public void performHide() {
        this.closeMessagePanel();
    }

    @Override
    public void performKickLeave(String nickName) {
        MercuryStoreCore.chatCommandSubject.onNext("/kick " + notificationDescriptor.getNickName());
    }

    @Override
    public void performOpenChat() {
        MercuryStoreCore.openChatSubject.onNext(notificationDescriptor.getNickName());
    }

    @Override
    public void performResponse(String response) {
        MercuryStoreCore.chatCommandSubject.onNext("@" + notificationDescriptor.getNickName() + " " + response);
    }

    @Override
    public void performGlobalChatResponse(String plusText) {
        // Send the "+text" to global chat (not as a whisper)
        // Need to prefix with "#" for global chat in Path of Exile
        MercuryStoreCore.chatCommandSubject.onNext("#" + plusText);
    }

    private void closeMessagePanel() {
        Timer timer = new Timer(30, action -> {
            MercuryStoreCore.removeScannerNotificationSubject.onNext(notificationDescriptor);
        });
        timer.setRepeats(false);
        timer.start();
    }
}
