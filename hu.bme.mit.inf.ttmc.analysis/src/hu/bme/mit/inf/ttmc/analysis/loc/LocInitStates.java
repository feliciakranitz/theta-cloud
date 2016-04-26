package hu.bme.mit.inf.ttmc.analysis.loc;

import java.util.Collection;
import java.util.Collections;

import hu.bme.mit.inf.ttmc.analysis.InitStates;
import hu.bme.mit.inf.ttmc.formalism.common.Automaton;
import hu.bme.mit.inf.ttmc.formalism.common.Loc;

public class LocInitStates<L extends Loc<L, ?>> implements InitStates<LocState<L>> {

	private final Automaton<L, ?> automaton;

	public LocInitStates(final Automaton<L, ?> automaton) {
		this.automaton = automaton;
	}

	@Override
	public Collection<? extends LocState<L>> get() {
		return Collections.singleton(LocState.create(automaton.getInitLoc()));
	}

}