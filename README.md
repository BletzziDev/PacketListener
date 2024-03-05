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
