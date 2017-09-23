/*
 * Copyright © 2013-2017, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package org.seedstack.business.internal.assembler.dsl;

import static org.junit.Assert.fail;

import com.google.common.collect.Sets;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.Whitebox;
import org.seedstack.business.assembler.AssemblerRegistry;
import org.seedstack.business.domain.AggregateNotFoundException;
import org.seedstack.business.domain.AggregateRoot;
import org.seedstack.business.domain.DomainRegistry;
import org.seedstack.business.domain.Repository;
import org.seedstack.business.fixtures.assembler.customer.Order;
import org.seedstack.business.fixtures.assembler.customer.OrderDto;
import org.seedstack.business.fixtures.assembler.customer.OrderDtoAssembler;
import org.seedstack.business.fixtures.assembler.customer.OrderFactoryInternal;
import org.seedstack.business.internal.assembler.dsl.resolver.AnnotationDtoInfoResolver;
import org.seedstack.business.spi.DtoInfoResolver;

public class MergeSingleAggregateFromRepositoryImplTest {

  private MergeSingleAggregateFromRepositoryImpl<Order, String, OrderDto> underTest;
  private Order order;
  private Repository<Order, String> repository;

  @SuppressWarnings("unchecked")
  @Before
  public void before() {
    DtoInfoResolver dtoInfoResolver = new AnnotationDtoInfoResolver();
    AssemblerRegistry assemblerRegistry = Mockito.mock(AssemblerRegistry.class);
    DomainRegistry domainRegistry = Mockito.mock(DomainRegistry.class);
    Context context = new Context(domainRegistry, assemblerRegistry,
      Sets.newHashSet(dtoInfoResolver));
    order = new Order("1", "death star");
    repository = Mockito.mock(Repository.class);

    Mockito.when(domainRegistry.getRepository(Order.class, String.class)).thenReturn(repository);
    Mockito.when(domainRegistry.getFactory(Order.class)).thenReturn(new OrderFactoryInternal());
    Whitebox.setInternalState(dtoInfoResolver, "domainRegistry", domainRegistry);
    Mockito.when(assemblerRegistry.getAssembler(Order.class, OrderDto.class))
      .thenReturn(new OrderDtoAssembler());

    underTest = new MergeSingleAggregateFromRepositoryImpl<>(context,
      new OrderDto("1", "lightsaber"), Order.class);
  }

  @Test
  public void testFromFactory() throws Exception {
    AggregateRoot<?> aggregateRoot = underTest.fromFactory();

    Assertions.assertThat(aggregateRoot).isNotNull();
    Assertions.assertThat(aggregateRoot).isEqualTo(order);

    Assertions.assertThat(((Order) aggregateRoot).getProduct()).isEqualTo("lightsaber");
  }

  @Test
  public void testOrFailOK() {
    Mockito.when(repository.get("1")).thenReturn(Optional.of(order));

    AggregateRoot<?> aggregateRoot = null;
    try {
      aggregateRoot = underTest.fromRepository().orFail();
    } catch (AggregateNotFoundException e) {
      fail();
    }

    Assertions.assertThat(aggregateRoot).isNotNull();
    Assertions.assertThat(aggregateRoot).isEqualTo(order);

    Assertions.assertThat(((Order) aggregateRoot).getProduct()).isEqualTo("lightsaber");
  }

  @Test
  public void testOrFailKO() {
    Mockito.when(repository.get("1")).thenReturn(Optional.empty());

    try {
      underTest.fromRepository().orFail();
      fail();
    } catch (AggregateNotFoundException e) {
      Assertions.assertThat(e.getMessage()).isNotEmpty();
    }

  }

  @Test
  public void testOrFromFactory() throws Exception {
    // Get it by the repository first
    Mockito.when(repository.get("1")).thenReturn(Optional.of(order));

    AggregateRoot<?> aggregateRoot = underTest.fromRepository().orFromFactory();

    Assertions.assertThat(aggregateRoot).isNotNull();
    Assertions.assertThat(aggregateRoot).isEqualTo(order);

    Assertions.assertThat(((Order) aggregateRoot).getProduct()).isEqualTo("lightsaber");
  }
}