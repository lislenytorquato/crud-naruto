package com.crud.naruto.interfaces;

import com.crud.naruto.model.Personagem;

public interface Ninja {

    String usarJutsu();
    String desviar(Personagem personagem, boolean conseguiuDesviar);

}
