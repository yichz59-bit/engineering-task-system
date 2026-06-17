<script setup lang="ts">
const modules = [
  {
    title: '项目管理',
    description: '管理项目生命周期、里程碑、成员和交付物。',
  },
  {
    title: '任务管理',
    description: '创建、分配、跟进任务状态和优先级。',
  },
  {
    title: '需求管理',
    description: '维护需求、评审进度与需求变更记录。',
  },
  {
    title: '数据看板',
    description: '展示关键指标、进度统计与工程健康状况。',
  },
]
</script>

<template>
  <main class="home-page">
    <section class="hero">
      <div class="hero-content">
        <p class="subtitle">Engineering Task System</p>
        <h1>工程任务管理系统</h1>
        <p class="hero-description">
          统一管理项目、任务、需求和看板，提升工程协作效率。
        </p>
      </div>
    </section>

    <section class="modules">
      <h2>功能模块</h2>
      <div class="module-grid">
        <article v-for="item in modules" :key="item.title" class="module-card">
          <h3>{{ item.title }}</h3>
          <p>{{ item.description }}</p>
        </article>
      </div>
    </section>
  </main>
</template>

<style scoped>
.home-page {
  padding: 2rem 1rem;
}

.hero {
  text-align: center;
  padding: 2rem 0 1.5rem;
}

.subtitle {
  color: #5c6b7a;
  margin-bottom: 0.5rem;
  text-transform: uppercase;
  letter-spacing: 0.2em;
  font-size: 0.9rem;
}

.hero h1 {
  margin: 0;
  font-size: clamp(2.4rem, 4vw, 3.5rem);
  color: #1f2937;
}

.hero-description {
  margin: 1rem auto 0;
  max-width: 640px;
  color: #4b5563;
  line-height: 1.75;
}

.modules {
  margin-top: 2.5rem;
}

.modules h2 {
  margin-bottom: 1rem;
  font-size: 1.5rem;
  color: #111827;
}

.module-grid {
  display: grid;
  gap: 1rem;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
}

.module-card {
  border: 1px solid #e5e7eb;
  border-radius: 16px;
  padding: 1.4rem;
  background: #ffffff;
  box-shadow: 0 14px 40px rgba(15, 23, 42, 0.04);
}

.module-card h3 {
  margin: 0 0 0.75rem;
  font-size: 1.1rem;
  color: #111827;
}

.module-card p {
  margin: 0;
  color: #475569;
  line-height: 1.7;
}
</style>
