package project_tennis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import project_tennis.config.BotConfig;
import project_tennis.model.Player;
import project_tennis.model.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class BotService extends TelegramLongPollingBot {

    @Autowired
    BotConfig botConfig;
    @Autowired
    PlayerRepository playerRepository;

    private HashMap<Long, ArrayList<String>> map = new HashMap<>();

    private ArrayList<Long> listOfUsers = new ArrayList<>();

    public BotService(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public String getBotUsername() {
        return botConfig.getBotName();
    }


    @Override
    public void onUpdateReceived(Update update) {
        long chatId = update.getMessage().getChatId();
        boolean hasText = update.hasMessage() && update.getMessage().hasText();
        String msgUser = update.getMessage().getText();

        if (hasText && !listOfUsers.contains(chatId)) {
            listOfUsers.add(chatId);
            sendMessage(chatId, "Добро пожаловать, " + update.getMessage().getChat().getUserName() + "!\n" + "Ссылка на чат - https://t.me/+nK37qMXwxRUxMGIy");
            sendMessage(chatId,
                    "Напишите свое имя, фамилию, возраст и уровень Вашей игры. " +
                            "\nГде 0 - Вы новичок, а 3 - Прекрасно играете. " +
                            "\nВ формате - Иван-Иванов 29 1");

//            sendMessage(chatId, "Добро пожаловать, "+ update.getMessage().getChat().getUserName()+"!");
        } else if (hasText && listOfUsers.contains(chatId)) {
            ArrayList<String> list = new ArrayList<>();
            sendMessage(chatId, "Спасибо за регистрацию!");
            list.add(update.getMessage().getText());
            addNameAndSurname(list.get(0), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
            map.put(chatId, list);
            listOfUsers.remove(chatId);
            map.remove(chatId);


        }

//        switch (msgUser) {
////            case "/register":
////                if (hasText && !listOfUsers.contains(chatId)) {
////                    listOfUsers.add(chatId);
////                    sendMessage(chatId,
////                            "Напишите свое имя и фамилию, возраст и уровень Вашей игры. " +
////                                    "\n0 - Только начали играть. " +
////                                    "\n1 - Удерживаете мяч в корте. " +
////                                    "\n2 - Способны пробить виннерс и имеете хорошую вторую подачу. " +
////                                    "\n3 - Есть первая и вторая подача, можете пробить бэкхенд и форхенд, умеете" +
////                                    " делать укороченные и резанные удары. " +
////                                    "\nВ формате Имя-Фамилия Возраст Уровень " +
////                                    "\nПример : Андрей-Викулов 33 3 " +
////                                    "\nВ одном сообщении!");
////
//////            sendMessage(chatId, "Добро пожаловать, "+ update.getMessage().getChat().getUserName()+"!");
////                } else if (hasText && listOfUsers.contains(chatId)) {
////                    ArrayList<String> list = new ArrayList<>();
//////            sendMessage(chatId, "Напишите свое имя и фамилию, возраст и уровень Вашей игры, где 0 - Только начали играть. 1 - Удерживаете мяч в корте. " +
//////                    "2 - Способны пробить виннерс и имеете хорошую вторую подачу. 3 - Есть и первая и вторая подача, можете пробить бэкхенд и форхенд, умеете" +
//////                    " делать укороченные и резанные удары. В формате [Имя Фамилия, 20, 1] В одном сообщении!");
////                    list.add(update.getMessage().getText());
////                    addNameAndSurname(list.get(0), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
////                    map.put(chatId, list);
////                    listOfUsers.remove(chatId);
////                    map.remove(chatId);
////
////                }
////                break;
//
//            case "/start":
//                sendMessage(chatId, "Меня зовут кукумбра!");
//                break;
//            case "/chat":
//                sendMessage(chatId, "Добро пожаловать, " + update.getMessage().getChat().getUserName() + "!\n" + "Ссылка на чат - https://t.me/+nK37qMXwxRUxMGIy");
//                break;
//
//            default:
//                sendMessage(chatId, "Неизвестная команда");
//
//        }

//        if(hasText&&!listOfUsers.contains(chatId)){
//            listOfUsers.add(chatId);
//            sendMessage(chatId, "Напишите свое имя и фамилию, возраст и уровень Вашей игры. \n0 - Только начали играть. \n1 - Удерживаете мяч в корте. " +
//                    "\n2 - Способны пробить виннерс и имеете хорошую вторую подачу. \n3 - Есть первая и вторая подача, можете пробить бэкхенд и форхенд, умеете" +
//                    " делать укороченные и резанные удары. \nВ формате Имя-Фамилия Возраст Уровень \nПример : Андрей-Викулов 33 3 \nВ одном сообщении!");
////            sendMessage(chatId, "Добро пожаловать, "+ update.getMessage().getChat().getUserName()+"!");
//        }
//        else {
//            ArrayList<String> list = new ArrayList<>();
////            sendMessage(chatId, "Напишите свое имя и фамилию, возраст и уровень Вашей игры, где 0 - Только начали играть. 1 - Удерживаете мяч в корте. " +
////                    "2 - Способны пробить виннерс и имеете хорошую вторую подачу. 3 - Есть и первая и вторая подача, можете пробить бэкхенд и форхенд, умеете" +
////                    " делать укороченные и резанные удары. В формате [Имя Фамилия, 20, 1] В одном сообщении!");
//            sendMessage(chatId, "Добро пожаловать, "+ update.getMessage().getChat().getUserName()+"!\n"+"Ссылка на чат - https://t.me/+nK37qMXwxRUxMGIy");
//            list.add(update.getMessage().getText());
//            addNameAndSurname(list.get(0), update.getMessage().getChat().getUserName(), update.getMessage().getChatId());
//            map.put(chatId, list);
//            listOfUsers.remove(chatId);
//            map.remove(chatId);
//        }
    }

    private void registerUser(Message message) {

//        if(playerRepository.findById(message.getChatId()).isEmpty()){
//            var chatId = message.getChatId();
//            var userName = message.getChat().getUserName();
//
//            Player player = new Player();
//
//            player.setChatId(chatId);
//            player.setUserName(userName);
//            playerRepository.sa
//        }

    }

    private void addNameAndSurname(String bio, String userName, Long chatId) {
        List<String> playersBio = new ArrayList<>();
        playersBio.add(bio + " " + userName);
//        for (String a: playersBio){
//            System.out.println(a);
//        }
        String[] infoAboutPlayer = new String[playersBio.size()];
        infoAboutPlayer = playersBio.get(0).split(" ");
//        for (Object e: infoAboutPlayer){
//            System.out.println(e);
//        }

        Player player = new Player();
        player.setUsername(userName);
        player.setChatId(String.valueOf(chatId));
        player.setName(infoAboutPlayer[0]);
        player.setAge(infoAboutPlayer[1]);
        player.setLevel(infoAboutPlayer[2]);

        playerRepository.save(player);

        String userNameTg = userName;

        String nameAndSurname = infoAboutPlayer[0];

        String age = infoAboutPlayer[1];

        String lvl = infoAboutPlayer[2];

        System.out.println(nameAndSurname);

        System.out.println(age);

        System.out.println(lvl);

        System.out.println(userNameTg);

    }


    private void sendMessage(Update update, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(update));
        sendMessage.setText(text);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void registerUser(long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText("Напишите свое имя и фамилию");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void startCommandReceived(long chatId, String userName) {
        String answer = "Привет " + userName + "!";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String answer) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(String.valueOf(chatId));
        sendMessage.setText(answer);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}