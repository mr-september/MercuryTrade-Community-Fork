package com.mercury.platform.ui.components.panel.taskbar;

import com.mercury.platform.TranslationKey;
import com.mercury.platform.shared.config.Configuration;
import com.mercury.platform.shared.config.configration.PlainConfigurationService;
import com.mercury.platform.shared.config.descriptor.TaskBarDescriptor;
import com.mercury.platform.shared.store.MercuryStoreCore;
import com.mercury.platform.ui.frame.movable.ItemsGridFrame;
import com.mercury.platform.ui.frame.movable.NotificationFrame;
import com.mercury.platform.ui.frame.movable.TaskBarFrame;
import com.mercury.platform.ui.frame.other.NotificationAlertFrame;
import com.mercury.platform.ui.frame.titled.ChatScannerFrame;
import com.mercury.platform.ui.frame.titled.HistoryFrame;
import com.mercury.platform.ui.frame.titled.SettingsFrame;
import com.mercury.platform.ui.frame.other.HelpIGFrame;
import com.mercury.platform.ui.manager.FramesManager;
import com.mercury.platform.ui.misc.MercuryStoreUI;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MercuryTaskBarController implements TaskBarController {
    private PlainConfigurationService<TaskBarDescriptor> taskBarService;

    public MercuryTaskBarController() {
        this.taskBarService = Configuration.get().taskBarConfiguration();
    }

    @Override
    public void enableDND() {
        MercuryStoreUI.repaintSubject.onNext(TaskBarFrame.class);
        MercuryStoreCore.alertSubject.onNext(TranslationKey.dnd_on.value());
        MercuryStoreCore.dndSubject.onNext(true);
    }

    @Override
    public void disableDND() {
        MercuryStoreUI.repaintSubject.onNext(TaskBarFrame.class);
        MercuryStoreCore.alertSubject.onNext(TranslationKey.dnd_off.value());
        MercuryStoreCore.dndSubject.onNext(false);
    }

    @Override
    public void enablePushbullet() {
        MercuryStoreUI.repaintSubject.onNext(TaskBarFrame.class);
        MercuryStoreCore.alertSubject.onNext(TranslationKey.pushbullet_notifications_on.value());
        MercuryStoreCore.pushbulletSubject.onNext(true);
        MercuryStoreCore.saveConfigSubject.onNext(true);
    }

    @Override
    public void disablePushbullet() {
        MercuryStoreUI.repaintSubject.onNext(TaskBarFrame.class);
        MercuryStoreCore.alertSubject.onNext(TranslationKey.pushbullet_notifications_off.value());
        MercuryStoreCore.pushbulletSubject.onNext(false);
        MercuryStoreCore.saveConfigSubject.onNext(true);
    }

    @Override
    public void showITH() {
        FramesManager.INSTANCE.enableOrDisableMovementDirect(ItemsGridFrame.class);
    }

    @Override
    public void performHideout() {
        MercuryStoreCore.chatCommandSubject.onNext("/hideout");
    }

    @Override
    public void showHelpIG() {
        FramesManager.INSTANCE.hideOrShowFrame(HelpIGFrame.class);
    }

    @Override
    public void showChatFiler() {
        FramesManager.INSTANCE.hideOrShowFrame(ChatScannerFrame.class);
    }

    @Override
    public void showHistory() {
        FramesManager.INSTANCE.hideOrShowFrame(HistoryFrame.class);
    }

    @Override
    public void openPINSettings() {
        FramesManager.INSTANCE.enableMovementExclude(ItemsGridFrame.class);
    }

    @Override
    public void openScaleSettings() {
        FramesManager.INSTANCE.enableScale();
    }

    @Override
    public void showSettings() {
        FramesManager.INSTANCE.showFrame(SettingsFrame.class);
    }

    @Override
    public void exit() {
        FramesManager.INSTANCE.exit();
    }

    @Override
    public void hideMessageNotifications() {
        FramesManager.INSTANCE.hideFrame(NotificationFrame.class);
    }

    @Override
    public void showMessageNotifications() {
        FramesManager.INSTANCE.showFrame(NotificationFrame.class);
    }

    @Override
    public void performJoinChannel() {
        String channelNumber = this.taskBarService.get().getJoinChannelNumber();
        MercuryStoreCore.chatCommandSubject.onNext("/global " + channelNumber);
    }
}
