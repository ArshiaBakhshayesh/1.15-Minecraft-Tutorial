package com.championash5357.tutorial.proxy;

import net.minecraftforge.eventbus.api.IEventBus;

public interface IProxy {

	void setup(final IEventBus forge, final IEventBus mod);
}
