# 📱 LifeSync Mobile

Aplicativo mobile React Native para o LifeSync AI Fitness Coach.

## 🏗️ Arquitetura

### Estrutura de Pastas
```
src/
├── components/          # Componentes UI reutilizáveis
│   └── common/         # Componentes genéricos
├── hooks/              # Hooks customizados
├── navigation/         # Configuração de navegação
├── screens/            # Telas do aplicativo
├── services/           # Serviços de API
├── types/              # Definições TypeScript
└── utils/              # Utilitários e temas
```

### Tecnologias
- **React Native** com TypeScript
- **Expo** para desenvolvimento
- **React Navigation** para navegação
- **React Native Paper** para UI components
- **Axios** para comunicação com API

### Telas Principais
- **LoginScreen** - Autenticação de usuário
- **DashboardScreen** - Painel principal com resumo
- **WorkoutsScreen** - Gestão de treinos
- **HabitsScreen** - Tracking de hábitos
- **ProgressScreen** - Análise de progresso
- **ProfileScreen** - Perfil e configurações

### Integração com Backend
- API RESTful conectada ao backend Spring Boot
- Autenticação JWT
- Serviços para workouts, hábitos e progresso

## 🚀 Como Executar

```bash
# Instalar dependências
npm install

# Iniciar desenvolvimento
npm start

# Rodar no Android
npm run android

# Rodar no iOS
npm run ios
```

## 📋 Features Implementadas

- ✅ Estrutura completa com TypeScript
- ✅ Navegação entre telas
- ✅ Integração com API backend
- ✅ Sistema de autenticação
- ✅ Componentes UI customizados
- ✅ Temas e estilização
- ✅ Telas principais funcionais
