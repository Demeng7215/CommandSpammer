package dev.demeng.commandspammer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class CommandSpammer extends JavaPlugin implements CommandExecutor {

  @Override
  public void onEnable() {
    Objects.requireNonNull(getCommand("spam")).setExecutor(this);
  }

  @Override
  public void onDisable() {}

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

    if (!cmd.getName().equalsIgnoreCase("spam")) {
      return true;
    }

    if (args.length < 2) {
      sender.sendMessage("Invalid usage. Did you mean: /spam <times> <command>");
      return true;
    }

    final int times;

    try {
      times = Integer.parseInt(args[0]);
    } catch (NumberFormatException ex) {
      sender.sendMessage("Times to spam must be a valid integer.");
      return true;
    }

    if (times < 1) {
      sender.sendMessage("Times to spam must be a positive integer (greater than 0).");
      return true;
    }

    final String command;
    final StringBuilder commandBuilder = new StringBuilder();
    commandBuilder.append(args[1]);

    for (int i = 2; i < args.length; i++) {
      commandBuilder.append(" ").append(args[i]);
    }

    command = commandBuilder.toString();

    for (int i = 0; i <= times; i++) {
      Bukkit.dispatchCommand(sender, command);
    }

    sender.sendMessage("Commands dispatched.");
    return true;
  }
}
