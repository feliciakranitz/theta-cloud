package hu.bme.mit.theta.analysis.sts;

import static com.google.common.base.Preconditions.checkNotNull;
import static hu.bme.mit.theta.core.expr.impl.Exprs.And;

import hu.bme.mit.theta.analysis.expr.ExprAction;
import hu.bme.mit.theta.core.expr.Expr;
import hu.bme.mit.theta.core.type.BoolType;
import hu.bme.mit.theta.formalism.sts.STS;

public class StsAction implements ExprAction {

	final Expr<? extends BoolType> trans;

	StsAction(final STS sts) {
		checkNotNull(sts);
		this.trans = And(sts.getTrans());
	}

	public Expr<? extends BoolType> getTrans() {
		return trans;
	}

	@Override
	public Expr<? extends BoolType> toExpr() {
		return trans;
	}

	@Override
	public String toString() {
		return trans.toString();
	}
}