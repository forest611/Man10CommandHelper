package red.man10.man10commandhelper

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Man10CommandHelper : JavaPlugin() {

    val commandList = HashMap<String,MutableList<String>>()

    override fun onEnable() {
        // Plugin startup logic

    }

    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        if (sender !is Player){
            return false
        }

        if (args == null || args.isEmpty()){
            sender.sendMessage("§d§lMan10CommandHelper")
            sender.sendMessage("§d§l/chelper save [id] [cmd1];[cmd2]...")
            sender.sendMessage("§d§lex)/chelper save id1 lobby;spawn")
            sender.sendMessage("§d§lスペースになるところはイコール(=)を入れてください")
            sender.sendMessage("§d§l使うときは、/chelper usingで使えます")
            sender.sendMessage("§d§lまた、任意のデータを使いたいときは、<custom>を入れてください")
            sender.sendMessage("§d§l/mhelper using data(任意のデータ)")

            return true
        }

        if (args[0] == "save" && args.size == 3){

            val id = args[1]

            val cmdList = mutableListOf<String>()
            val commands = args[2].split(";")
            for (cmd in commands){
                cmdList.add(cmd.replace("=",  " "))
            }
            commandList[id] = cmdList
            return true
        }

        if (args[0] == "using"){
            for (cmd in commandList[args[1]]!!){
                if (args.size == 3){
                    sender.performCommand(cmd.replace("<custom>",args[2]))
                }else{
                    sender.performCommand(cmd)
                }
            }
            return true
        }

        if (args[0] == "list"){
            for (id in commandList.keys){
                sender.sendMessage("§e§l$id")
            }
            return true
        }
        
        if (args[0] == "delete"){
            commandList.remove(args[1])
            return true
        }




        return false
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
