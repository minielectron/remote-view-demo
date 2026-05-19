# Graph Report - .  (2026-05-19)

## Corpus Check
- 9 files · ~3,332 words
- Verdict: corpus is large enough that graph structure adds value.

## Summary
- 32 nodes · 23 edges · 9 communities detected
- Extraction: 100% EXTRACTED · 0% INFERRED · 0% AMBIGUOUS
- Token cost: 0 input · 0 output

## Community Hubs (Navigation)
- [[_COMMUNITY_Community 0|Community 0]]
- [[_COMMUNITY_Community 1|Community 1]]
- [[_COMMUNITY_Community 2|Community 2]]
- [[_COMMUNITY_Community 3|Community 3]]
- [[_COMMUNITY_Community 4|Community 4]]
- [[_COMMUNITY_Community 5|Community 5]]
- [[_COMMUNITY_Community 6|Community 6]]
- [[_COMMUNITY_Community 7|Community 7]]
- [[_COMMUNITY_Community 8|Community 8]]

## God Nodes (most connected - your core abstractions)
1. `CustomNotificationHelper` - 8 edges
2. `NotificationState` - 5 edges
3. `MainActivity` - 4 edges
4. `ExampleInstrumentedTest` - 2 edges
5. `ExampleUnitTest` - 2 edges
6. `NotificationActionReceiver` - 2 edges

## Surprising Connections (you probably didn't know these)
- None detected - all connections are within the same source files.

## Communities

### Community 0 - "Community 0"
Cohesion: 0.22
Nodes (1): CustomNotificationHelper

### Community 1 - "Community 1"
Cohesion: 0.33
Nodes (1): NotificationState

### Community 2 - "Community 2"
Cohesion: 0.4
Nodes (1): MainActivity

### Community 3 - "Community 3"
Cohesion: 0.67
Nodes (1): ExampleInstrumentedTest

### Community 4 - "Community 4"
Cohesion: 0.67
Nodes (1): ExampleUnitTest

### Community 5 - "Community 5"
Cohesion: 0.67
Nodes (1): NotificationActionReceiver

### Community 6 - "Community 6"
Cohesion: 1.0
Nodes (0): 

### Community 7 - "Community 7"
Cohesion: 1.0
Nodes (0): 

### Community 8 - "Community 8"
Cohesion: 1.0
Nodes (0): 

## Knowledge Gaps
- **Thin community `Community 6`** (1 nodes): `build.gradle.kts`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 7`** (1 nodes): `settings.gradle.kts`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.
- **Thin community `Community 8`** (1 nodes): `build.gradle.kts`
  Too small to be a meaningful cluster - may be noise or needs more connections extracted.

## Suggested Questions
_Not enough signal to generate questions. This usually means the corpus has no AMBIGUOUS edges, no bridge nodes, no INFERRED relationships, and all communities are tightly cohesive. Add more files or run with --mode deep to extract richer edges._