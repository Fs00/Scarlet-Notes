package com.maubis.scarlet.base.security

import android.app.Dialog
import com.facebook.litho.Column
import com.facebook.litho.Component
import com.facebook.litho.ComponentContext
import com.facebook.litho.widget.Text
import com.facebook.yoga.YogaEdge
import com.maubis.scarlet.base.R
import com.maubis.scarlet.base.ScarletApp.Companion.appPreferences
import com.maubis.scarlet.base.ScarletApp.Companion.appTheme
import com.maubis.scarlet.base.ScarletApp.Companion.appTypeface
import com.maubis.scarlet.base.support.sheets.LithoBottomSheet
import com.maubis.scarlet.base.support.sheets.getLithoBottomSheetTitle
import com.maubis.scarlet.base.support.specs.BottomSheetBar
import com.maubis.scarlet.base.support.ui.ThemeColorType
import com.maubis.scarlet.base.support.ui.ThemedActivity

const val STORE_KEY_NO_PIN_ASK = "KEY_NO_PIN_ASK"
var sNoPinSetupNoticeShown: Boolean
  get() = appPreferences.get(STORE_KEY_NO_PIN_ASK, false)
  set(value) = appPreferences.put(STORE_KEY_NO_PIN_ASK, value)

class NoPincodeBottomSheet : LithoBottomSheet() {
  var onSuccess: () -> Unit = {}

  override fun getComponent(componentContext: ComponentContext, dialog: Dialog): Component {
    val activity = context as ThemedActivity
    val component = Column.create(componentContext)
      .widthPercent(100f)
      .paddingDip(YogaEdge.VERTICAL, 8f)
      .paddingDip(YogaEdge.HORIZONTAL, 20f)
      .child(
        getLithoBottomSheetTitle(componentContext)
          .textRes(R.string.no_pincode_sheet_title)
          .marginDip(YogaEdge.HORIZONTAL, 0f))
      .child(
        Text.create(componentContext)
          .typeface(appTypeface.text())
          .textSizeRes(R.dimen.font_size_large)
          .textRes(R.string.no_pincode_sheet_details)
          .marginDip(YogaEdge.BOTTOM, 16f)
          .textColor(appTheme.get(ThemeColorType.TERTIARY_TEXT)))
      .child(BottomSheetBar.create(componentContext)
               .primaryActionRes(R.string.no_pincode_sheet_set_up)
               .onPrimaryClick {
                 openCreateSheet(
                   activity = activity,
                   onCreateSuccess = {})
                 dismiss()
               }
               .secondaryActionRes(R.string.no_pincode_sheet_dont_ask)
               .onSecondaryClick {
                 onSuccess()
                 dismiss()
               }
               .tertiaryActionRes(R.string.no_pincode_sheet_not_now)
               .onTertiaryClick {
                 onSuccess()
                 dismiss()
               }
               .paddingDip(YogaEdge.VERTICAL, 8f))
    return component.build()
  }
}
