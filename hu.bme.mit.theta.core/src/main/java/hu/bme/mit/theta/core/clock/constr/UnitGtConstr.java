package hu.bme.mit.theta.core.clock.constr;

import static hu.bme.mit.theta.core.type.rattype.RatExprs.Gt;
import static hu.bme.mit.theta.core.type.rattype.RatExprs.Rat;

import hu.bme.mit.theta.core.decl.VarDecl;
import hu.bme.mit.theta.core.type.anytype.RefExpr;
import hu.bme.mit.theta.core.type.rattype.RatGtExpr;
import hu.bme.mit.theta.core.type.rattype.RatType;

public final class UnitGtConstr extends UnitConstr {

	private static final int HASH_SEED = 9161;

	private static final String OPERATOR_LABEL = ">";

	private volatile RatGtExpr expr = null;

	UnitGtConstr(final VarDecl<RatType> clock, final int bound) {
		super(clock, bound);
	}

	@Override
	public RatGtExpr toExpr() {
		RatGtExpr result = expr;
		if (result == null) {
			final RefExpr<RatType> ref = getVar().getRef();
			result = Gt(ref, Rat(getBound(), 1));
			expr = result;
		}
		return result;
	}

	@Override
	public <P, R> R accept(final ClockConstrVisitor<? super P, ? extends R> visitor, final P param) {
		return visitor.visit(this, param);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		} else if (obj instanceof UnitGtConstr) {
			final UnitGtConstr that = (UnitGtConstr) obj;
			return this.getBound() == that.getBound() && this.getVar().equals(that.getVar());
		} else {
			return false;
		}
	}

	@Override
	protected int getHashSeed() {
		return HASH_SEED;
	}

	@Override
	protected String getOperatorLabel() {
		return OPERATOR_LABEL;
	}

}