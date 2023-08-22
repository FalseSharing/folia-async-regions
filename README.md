# folia-async-regions

High-performance regional block modifications and chunk batch pipelines natively supporting Paper and Folia regional multithreading.

### Overview

Standard block operations on Folia require dispatching tasks onto specific regional thread pools owning the chunk coordinates. This plugin provides an asynchronous batch queue that slices geometric selections by chunk boundaries and executes operations within the correct Folia region thread without blocking main ticks.

### Features

* **Folia Regional Thread Safety:** Automatic routing via `RegionScheduler` with fallback to standard `BukkitScheduler` on Paper/Purpur.
* **Chunk Boundary Slicing:** Multi-chunk selections are decoupled into independent chunk tasks for parallel tick processing.
* **Zero Tick Hitching:** Rate-limited batch iterations respecting server tick budgets.

### Requirements

* Java 21+
* Paper or Folia 1.20.4+ / 1.21+

### Commands & Permissions

* `/asyncfill <material>` &mdash; Fill selected region chunks asynchronously (`asyncregions.command.fill`).
