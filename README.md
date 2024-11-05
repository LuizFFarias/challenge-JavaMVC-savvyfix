# SavvyFix - Precificação dinâmica

## Lista dos integrantes:
#### 1. Douglas Magalhães de Araujo - 552008

#### 2. Erik Yuuzo Kobayachi Yamada - 98027

#### 2. Gustavo Argüello Bertacci - 551304

#### 3. Luiz Fillipe Farias - 99519

#### 4. Rafaella Monique do Carmo Bastos - 552425

<br>

## Link do vídeo da aplicação Java MVC
https://youtu.be/DageQYzsqSo?si=6AdUCxAX2bccbJxu

## Link do vídeo do Deploy via Azure Pipeline
https://youtu.be/A_BI4EeVzFk

<br>

## Instruções testar a aplicação

<p>Primeiramente inicie o projeto na sua IDE e vá em algum browser e busque o endereço: http://localhost:8080
  
![image](https://github.com/user-attachments/assets/e821f91b-6c7d-4a13-909c-139b1fdb16e8)

<br>

Faça o login com um usuário cadastrado

![image](https://github.com/user-attachments/assets/e7b48947-e2fe-4195-a01b-e2e84615dd65)

<br>

Clique em "Ver Produtos"

![image](https://github.com/user-attachments/assets/6abb5cf3-7eaa-4a63-beb4-1f4fa3cec7be)

<br>

Clique em comprar

![image](https://github.com/user-attachments/assets/5501e58f-779b-4ec1-9ed6-37a44c782d92)

<br>

Escolha a quantidade e clique em "Finalizar Compra"

![image](https://github.com/user-attachments/assets/5a0cf5ad-f4bd-4931-bf0d-86a67de0e684)

<br>

Aqui podemos ver o histórico de compras, agora ao comprar o mesmo produto novamente ele estará com 10% de desconto 
por conta da precificação dinâmica

![image](https://github.com/user-attachments/assets/8a9870d2-eadf-41a8-8d6e-230476473e64)

Após realizar a nova compra podemos ver o histórico com as compras de valores diferentes

![image](https://github.com/user-attachments/assets/d6f2a4f6-ffba-4572-9779-e5adb03b0442)
</p>

## Diagrama de classes
![DiagramaDeClasses](https://github.com/user-attachments/assets/ec004b70-08e7-4c68-8690-d93a9d32ea48)

<br>

## Arquitetura do sistema
![Arquitetura_Java](https://github.com/user-attachments/assets/9c992f51-3f8a-4808-83d7-eab1ad0d10e6)

<br>

## Instruções para o deploy da aplicação
<p>1. Copie o link deste repositório e clone no Intellij ou outra IDE semelhante.</p>
<p>2. Clique com o botão direito no nome do projeto.</p>
<p>3. Selecione a opção Azure e clique em "Deploy to Azure Container Apps..."</p>
<p>4. Preencha o nome da solução</p>
<p>5. Clique em + para adicionar um webapp e preencha seu nome</p>
<p>6. Clique em "Mais configurações" e preencha as informações de resource group, plataforma, região e plano.</p>
<p>7. Clique em "Run" para rodar o projeto.</p>
<p>8. Após todas as informações rodarem no terminal, clique na URL que aparecerá.</p>
