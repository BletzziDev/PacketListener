# ENGLISH TUTORIAL

# How to install?

As the lib has not yet been installed in a maven repository, the installation must be done manually.
Just download the .zip file from the source and copy the packetlistener package to your project, after which the lib will have been successfully installed.

# How to use?

## Main instance

Firstly, you need to instantiate an object to control the listeners, follow the example below in your plugin main class:

```java
public PacketListenerController packetListenerController;
@Override
public void onEnable() {
  packetListenerController = new PacketListenerController(this);
}
```

## Add packet listener

To add a packet listener, it's very simple, just use the ```addPacketListener()``` method of your ```PacketListenerController``` instance.

### Example

```java
@Override
    public void onEnable() {
        packetListenerController = new PacketListenerController(this);
        packetListenerController.addPacketListener(PacketPlayInBlockPlace.class, new PacketListener((packetData)->{
            PacketPlayInBlockPlace packet = (PacketPlayInBlockPlace) packetData.getPacket();
            double blockX = packet.a().getX();
            Bukkit.getConsoleSender().sendMessage(String.format("BLOCO POSIÇÃO X: %s", blockX));
        }));
    }
```


# TUTORIAL EM PORTUGUES

# Como instalar?

Como no momento a lib ainda não foi instalada em um repositório maven, a instalação deve ser feita manualmente.
Basta baixar o arquivo .zip da source e copiar a package packetlistener para o seu projeto, após isso a lib já terá sido instalada com sucesso.

# Como utilizar?

## Instância principal

Primeiramente é necessário que você instancie um objeto para controlar os listeners, siga o exemplo abaixo na sua Main:

```java
public PacketListenerController packetListenerController;
@Override
public void onEnable() {
  packetListenerController = new PacketListenerController(this);
}
```

## Listener de pacotes

Para adicionar um listener de pacotes é muito simples, basta usar o método ```addPacketListener()``` da sua instancia do ```PacketListenerController```.

### Exemplo

```java
@Override
    public void onEnable() {
        packetListenerController = new PacketListenerController(this);
        packetListenerController.addPacketListener(PacketPlayInBlockPlace.class, new PacketListener((packetData)->{
            PacketPlayInBlockPlace packet = (PacketPlayInBlockPlace) packetData.getPacket();
            double blockX = packet.a().getX();
            Bukkit.getConsoleSender().sendMessage(String.format("BLOCO POSIÇÃO X: %s", blockX));
        }));
    }
```
