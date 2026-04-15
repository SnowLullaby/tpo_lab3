# tpo_lab3

## Основные сущности проекта
- `Utils` - поднимает и закрывает браузер, настраивает таймауты.
- `HomePage` - главная страница PromoPult.
- `LoginForm` - форма входа пользователя.
- `RegistrationForm` - форма регистрации пользователя.
- `PasswordRecoveryPage` - страница восстановления пароля.
- `FeaturesPage` - страница, на которой расположен отдельный список инструментов ниже заголовка.
- `BlogPage` - блог PromoPult.
- `CasesPage` - страница кейсов.
- `BonusProgramPage` - страница бонусной программы.
- `EducationPage` - раздел обучения.
- `HelpCenterPage` - справочный центр PromoPult.

## Ограничения
- Регистрация и авторизация полностью не автоматизируются из-за CAPTCHA.
- Положительная авторизация требует актуального реального пароля.
- Положительное восстановление пароля отправляет реальное письмо и поэтому выключено по умолчанию.
- Подбор кейсов по адресу сайта в автотесты не включён из-за долгого ожидания результата.

## Системные свойства
По умолчанию используются такие значения:
- `browser=chrome`
- `headless=false`
- `baseUrl=https://promopult.ru/`
- `authEmail=simeon.gory@gmail.com`
- `authPassword=QaTest123!`
- `enableLiveAuth=false`
- `enableLiveRecovery=false`
- `runProtectedFlows=false`

## Как запускать
### Chrome
```bash
mvn clean test -Dbrowser=chrome
```

### Firefox
```bash
mvn clean test -Dbrowser=firefox
```

### Headless
```bash
mvn clean test -Dbrowser=chrome -Dheadless=true
```

### Положительный вход
```bash
mvn clean test -Dbrowser=chrome -DenableLiveAuth=true -DauthEmail=simeon.gory@gmail.com -DauthPassword=REAL_PASSWORD
```

### Положительное восстановление пароля
```bash
mvn clean test -Dbrowser=chrome -DenableLiveRecovery=true -DauthEmail=simeon.gory@gmail.com
```

## Замечание
Сайт живой и местами неоднородный по структуре из-за редиректов и разных типов страниц. 
XPath-локаторы подобраны под текущую публичную версию, но при изменениях DOM их может понадобиться скорректировать.
