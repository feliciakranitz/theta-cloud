package hu.bme.mit.theta.frontend.benchmark;

import static java.util.Collections.emptyList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import hu.bme.mit.theta.analysis.Action;
import hu.bme.mit.theta.analysis.Precision;
import hu.bme.mit.theta.analysis.State;
import hu.bme.mit.theta.analysis.algorithm.SafetyStatus;
import hu.bme.mit.theta.analysis.algorithm.Statistics;
import hu.bme.mit.theta.common.logging.Logger;
import hu.bme.mit.theta.common.logging.impl.ConsoleLogger;
import hu.bme.mit.theta.formalism.sts.STS;
import hu.bme.mit.theta.formalism.sts.dsl.StsDslManager;
import hu.bme.mit.theta.formalism.sts.dsl.impl.StsSpec;
import hu.bme.mit.theta.formalism.sts.utils.impl.StsIteTransformation;
import hu.bme.mit.theta.frontend.aiger.impl.SimpleAigerLoader;
import hu.bme.mit.theta.frontend.benchmark.StsConfigurationBuilder.Domain;
import hu.bme.mit.theta.frontend.benchmark.StsConfigurationBuilder.Refinement;
import hu.bme.mit.theta.frontend.benchmark.StsConfigurationBuilder.Search;

public class StsBenchmark {

	public static void main(final String[] args) {
		final String basePath = "src/test/resources/";
		final Logger logger = new ConsoleLogger(100);
		final int runs = 1;

		final List<StsInput> inputs = new ArrayList<>();
		inputs.add(new StsInput(basePath + "simple/counter.system", true));
		inputs.add(new StsInput(basePath + "simple/counter_bad.system", false));
		inputs.add(new StsInput(basePath + "simple/counter_parametric.system", true));
		inputs.add(new StsInput(basePath + "simple/boolean1.system", false));
		inputs.add(new StsInput(basePath + "simple/boolean2.system", false));
		inputs.add(new StsInput(basePath + "simple/readerswriters.system", true));
		inputs.add(new StsInput(basePath + "simple/loop.system", true));
		inputs.add(new StsInput(basePath + "simple/loop_bad.system", false));

		inputs.add(new StsInput(basePath + "simple/simple1.system", false));
		inputs.add(new StsInput(basePath + "simple/simple2.system", true));
		inputs.add(new StsInput(basePath + "simple/simple3.system", false));

		final List<StsConfigurationBuilder> builders = new ArrayList<>();
		builders.add(new StsConfigurationBuilder(Domain.PRED, Refinement.CRAIGITP).search(Search.BFS));
		builders.add(new StsConfigurationBuilder(Domain.PRED, Refinement.CRAIGITP).search(Search.DFS));
		builders.add(new StsConfigurationBuilder(Domain.PRED, Refinement.SEQITP).search(Search.BFS));
		builders.add(new StsConfigurationBuilder(Domain.PRED, Refinement.SEQITP).search(Search.DFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.CRAIGITP).search(Search.BFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.CRAIGITP).search(Search.DFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.SEQITP).search(Search.BFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.SEQITP).search(Search.DFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.UNSATCORE).search(Search.BFS));
		builders.add(new StsConfigurationBuilder(Domain.EXPL, Refinement.UNSATCORE).search(Search.DFS));

		run(inputs, builders, logger, runs);
	}

	private static void run(final Iterable<StsInput> inputs, final Iterable<StsConfigurationBuilder> configBuilders,
			final Logger logger, final int runs) {
		for (final StsInput input : inputs) {
			for (final StsConfigurationBuilder builder : configBuilders) {
				for (int i = 0; i < runs; ++i) {
					run(input, builder, logger);
				}
			}
		}
	}

	private static void run(final StsInput input, final StsConfigurationBuilder configBuilder, final Logger logger) {
		logger.write(input.path, 0);
		logger.write("," + configBuilder.getDomain(), 0);
		logger.write("," + configBuilder.getRefinement(), 0);
		logger.write("," + configBuilder.getSearch(), 0);
		try {
			final STS sts = input.load();
			final Configuration<? extends State, ? extends Action, ? extends Precision> configuration = configBuilder
					.build(sts);
			final SafetyStatus<? extends State, ? extends Action> status = configuration.check();

			if (status.isSafe() != input.expected) {
				logger.writeln(",FALSE", 0);
			} else {
				final Statistics stats = status.getStats().get();
				logger.writeln(String.format(",%d,%d", stats.getElapsedMillis(), stats.getIterations()), 0);
			}

		} catch (final IOException e) {
			logger.writeln(",IOEXCEPTION", 0);
		}

	}

	public static class StsInput {
		public final String path;
		public final boolean expected;

		public StsInput(final String path, final boolean expected) {
			this.path = path;
			this.expected = expected;
		}

		public STS load() throws IOException {
			if (path.endsWith(".aag")) {
				return new SimpleAigerLoader().load(path);
			} else {
				final StsSpec spec = StsDslManager.parse(path, emptyList());
				assert spec.getAllSts().size() == 1;
				return new StsIteTransformation().transform(spec.getAllSts().iterator().next());
			}
		}
	}
}