# Version 2.3.3 (2016-08-10)

* [fix] Also bind event handler classes which ancestors implement the `EventHandler` interface

# Version 2.3.2 (2016-08-03)

* [fix] Prevent binding of abstract classes and interfaces extending/implementing `EventHandler`

# Version 2.3.1 (2016-07-29)

* [chg] Updated the signature of the `handle()` method of `IdentityHandler`.
* [fix] Avoid sending domain events multiple times when multiple handlers are registered.

# Version 2.3.0 (2016-04-25)

* [new] Add methods `exists()`, `count()` and `clear()` on `Repository`
* [brk] The `Comparable` interface have been removed from `BaseValueObject`
* [brk] Methods `do*()` on `BaseRepository` have been removed. Directly implement their matching method on `Repository`.
* [chg] Remove `final` qualifier on `equals()` and `hashCode()` methods of `BaseValueObject` and `BaseEntity`.
* [chg] Changed the default `toString()` of `BaseValueObject` and `BaseEntity` with more concise output.
* [chg] The `business-web` module has been merged into `business-core` module (the dependency can be safely removed from your poms).
* [chg] `serialVersionUID` of event classes (`org.seedstack.business.domain.events` package) has been changed to 1L.
* [chg] Default repositories are now also created for parent classes of aggregate roots

# Version 2.2.0 (2016-01-21)

* [new] `BaseRangeFinder` is a persistence-agnostic base class for paginated finders. It notably replaces `BaseJpaRangeFinder` from the JPA add-on.

# Version 2.1.0 (2015-11-26)

* [brk] Remove `org.seedstack.business.api.BaseClassSpecifications`. Use `org.seedstack.seed.core.utils.BaseClassSpecifications` instead.
* [brk] `business-test` module is merged into `business-core` module.
* [brk] `business-jpa` module is merged into `jpa` addon.

# Version 2.0.0 (2015-07-30)

* [nfo] Initial Open-Source version.
