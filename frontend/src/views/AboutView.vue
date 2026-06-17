<template>
  <main class="about-page">
    <h1>关于系统</h1>
    <p>
      本系统用于工程任务、需求、模型对象和流程状态管理，帮助团队统一管理项目进度、任务分配、需求追踪与数据看板。
    </p>
    <section class="about-details">
      <div>
        <h2>工程任务</h2>
        <p>支持任务创建、指派、执行和状态跟踪。</p>
      </div>
      <div>
        <h2>需求管理</h2>
        <p>维护需求文档、评审记录及版本变更。</p>
      </div>
      <div>
        <h2>模型对象</h2>
        <p>管理项目、团队、交付物等工程对象关系。</p>
      </div>
      <div>
        <h2>流程状态</h2>
        <p>可视化流程节点与当前进度，辅助决策和协作。</p>
      </div>
    </section>
  </main>
</template>

<style scoped>
.about-page {
  padding: 2rem 1rem;
  max-width: 840px;
  margin: 0 auto;
}

.about-page h1 {
  margin-bottom: 1rem;
  font-size: clamp(2rem, 3vw, 2.5rem);
  color: #111827;
}

.about-page p {
  margin: 0 0 2rem;
  color: #475569;
  line-height: 1.75;
  font-size: 1rem;
}

.about-details {
  display: grid;
  gap: 1rem;
  grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
}

.about-details div {
  padding: 1.2rem;
  border-radius: 16px;
  background: #f8fafc;
  border: 1px solid #e2e8f0;
}

.about-details h2 {
  margin: 0 0 0.75rem;
  font-size: 1.05rem;
  color: #0f172a;
}

.about-details p {
  margin: 0;
  color: #475569;
  line-height: 1.7;
}
</style>
