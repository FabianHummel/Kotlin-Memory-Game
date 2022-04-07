package spg.view.dialog

import javafx.event.ActionEvent
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.stage.WindowEvent
import spg.model.PlayerProfile
import spg.view.panes.LoadGamePane
import spg.view.panes.NewGamePane
import spg.view.panes.SettingsPane
import kotlin.system.exitProcess

class LauncherDialog : CustomDialogBase<PlayerProfile>() {

	private lateinit var newGamePane : NewGamePane
	private lateinit var loadGamePane : LoadGamePane
	lateinit var settingsPane : SettingsPane

	override fun content() : Node {
		return TabPane(
			Tab(
				"New Game",
				NewGamePane(
					this@LauncherDialog
				).apply {
					newGamePane = this
				}
			),

			Tab(
				"Load Game",
				LoadGamePane(
					this@LauncherDialog
				).apply {
					loadGamePane = this
				}
			),

			Tab(
				"Settings",
				SettingsPane(
					this@LauncherDialog
				).apply {
					settingsPane = this
				}
			)
		).apply {
			this.padding = Insets(0.01)
			this.tabClosingPolicy = TabPane.TabClosingPolicy.UNAVAILABLE
		}
	}

	override fun onClose(event : WindowEvent) {
		exitProcess(0)
	}

	override fun onCancel(event : ActionEvent) {
		exitProcess(0)
	}
}