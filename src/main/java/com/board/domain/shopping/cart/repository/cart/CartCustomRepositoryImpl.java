package com.board.domain.shopping.cart.repository.cart;

import com.board.domain.shopping.cart.entity.Cart;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

import static com.board.domain.shopping.cart.entity.QCart.cart;
import static com.board.domain.member.entity.QMember.member;

public class CartCustomRepositoryImpl implements CartCustomRepository{

    JPAQueryFactory jpaQueryFactory;

    public CartCustomRepositoryImpl(EntityManager em) {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Cart findByMemberUUID(UUID memberUUID) {
        Cart cart1 = jpaQueryFactory.selectFrom(cart)
                .leftJoin(cart.member, member)
                .where(cart.member.memberUUID.eq(memberUUID))
                .fetchOne();
        return cart1;
    }
}
