# Proposal: AI-Assisted Development Workflow (HCP Mixed Version)

**Date**: 2026-04-24  
**Project**: Embabel Agent Framework  
**Status**: 📝 Under Review  

---

## 🎯 The Challenge: Accepting AI-Assisted Contributions Without the "Slop"

As an AI agent framework, Embabel naturally attracts developers interested in AI. Many of these contributors use AI coding assistants (Cursor, Copilot, Claude) to explore the codebase and write code. However, this introduces two critical problems for maintainers:

1. **Token Burn & Blind Exploration**: When external contributors use AI to find "how to implement X," the AI often blindly indexes dozens of files, burning massive amounts of tokens (e.g., ~50K tokens per question) and still missing the architectural nuance of Embabel.
2. **AI "Slop" and Friction**: Because the AI lacks the specific architectural context, it hallucinates patterns, ignores existing abstractions, or writes code that technically compiles but violates the framework's design. This forces maintainers to spend hours reviewing and correcting PRs that feel "alien" to the codebase.

## 💡 The Solution: HCP "Mixed Version" (Human-Code-AI Protocol v1.0)

We propose implementing a **lightweight, mixed version of the HCP Protocol**. The goal is to provide AI assistants with a strict, curated "map" of Embabel's architecture so they generate high-quality contributions from the very first prompt, without forcing human developers to change how they work.

This approach leverages two standardized concepts to organize context:
* **KDD (Knowledge-Driven Development)**: Captures stable domain knowledge, architecture, and API behaviors (e.g., Harness architecture, `EmbabelObservationContext`).
* **SDD (Specification-Driven Development)**: Captures iterative features, ADRs (Architecture Decision Records), and active specs.

### What is the "Mixed Version"?

This implementation relies on a static `.procontext/` directory and an optional `.cursorrules` file.

1. **Static, Cacheable Context**: 
   Instead of letting the AI guess the architecture, we provide a curated snapshot using KDD principles: `context.md`, `session.md`, and `AGENTS.md`. 
   * **Token Savings**: The AI reads this context once per session (~15K tokens). Thanks to *Prompt Caching* in modern LLMs, subsequent interactions cost **~90% less** (~1.5K tokens). The contributor saves money, and the AI understands the project instantly.

2. **Strict AI Roles (`AGENTS.md`)**:
   We limit AI hallucinations by assigning specific personas. If a contributor asks the AI to implement a new `ObservationUtils` test, the AI assumes the **IMPLEMENTER** role, which *strictly forbids* architectural changes. If they need to design a new agent harness, they use the **ARCHITECT** role. This prevents the AI from generating massive, out-of-scope refactors.

3. **Zero Bureaucracy for Humans**:
   * **Human Contributor**: Simply reads `context.md` (5 min) to understand the project structure (Kotlin/Java, Maven, core concepts). Then, they code as usual.
   * **AI Assistant**: At the end of the day (or PR), an automated workflow allows the AI to read the `git diff` and update the `session.md` (SDD state) automatically, keeping documentation alive with zero human typing.

---

## 🛠️ How this integrates without frustrating maintainers

Our core premise is: **"HCP is a tool, not bureaucracy."**

### 1. The Workflow for Traditional Contributors
If a developer prefers to write code without AI or context tracking:
- They completely ignore the `.procontext/` directory.
- They submit PRs as usual.
- A maintainer (or an automated AI reviewer) can effortlessly sync the new architectural changes into `.procontext/` (updating KDD/SDD records) during the merge.

### 2. The Workflow for AI-Assisted Contributors (Cursor IDE / Copilot)
- The contributor opens the project.
- The IDE automatically reads `.cursorrules` (enforcing Java/Kotlin conventions, Mockito, JUnit 5, AAA testing pattern).
- When the developer asks: "Add test coverage for `Export.java`", the AI already knows it needs `@Retention(RetentionPolicy.RUNTIME)` for reflection because it read the framework's testing strategy in the KDD context. **First-try success = Fewer PR revisions.**

### 3. The RPI+ Workflow (Research, Plan, Implement)
We inject the **RPI+** workflow into the AI's instructions.
Instead of immediately outputting code (which often leads to hallucinated dependencies), the AI must:
1. **R**esearch: Grep the codebase for similar patterns (e.g., how other annotations are tested).
2. **P**lan: Propose the component structure and ask for approval.
3. **I**mplement: Write the code following Embabel's conventions.

---

## 📈 Expected Results for Embabel

- **Drastically higher quality PRs** from external contributors using AI, as the AI acts as a pre-reviewer aligned with Embabel's specific architecture.
- **80-90% reduction in token costs** for contributors exploring the repo via chat, thanks to centralized context caching.
- **30-minute onboarding** for new developers, as the `.procontext/` holds the "why" behind decisions, not just the "what".

> *Note: This proposal is designed to show how a structured context protocol can bridge the gap between human maintainers and AI-assisted external contributors, ensuring code quality without imposing rigid manual documentation requirements.*
