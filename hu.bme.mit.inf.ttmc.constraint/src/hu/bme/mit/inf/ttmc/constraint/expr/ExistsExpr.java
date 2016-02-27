package hu.bme.mit.inf.ttmc.constraint.expr;

import hu.bme.mit.inf.ttmc.constraint.type.BoolType;
import hu.bme.mit.inf.ttmc.constraint.utils.ExprVisitor;

public interface ExistsExpr extends QuantifiedExpr {
	
	@Override
	public ExistsExpr withOp(Expr<? extends BoolType> op);
	
	@Override
	public default <P, R> R accept(ExprVisitor<? super P, ? extends R> visitor, P param) {
		return visitor.visit(this, param);
	}
}