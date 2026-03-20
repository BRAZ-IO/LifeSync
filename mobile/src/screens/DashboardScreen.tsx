import React, { useEffect, useRef } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ScrollView,
  Dimensions,
  TouchableOpacity,
  SafeAreaView,
  StatusBar,
  Animated,
  Easing,
} from 'react-native';
import { Card, Button, FAB, ProgressBar } from 'react-native-paper';
import { Ionicons } from '@expo/vector-icons';
import { LinearGradient } from 'expo-linear-gradient';

const { width, height } = Dimensions.get('window');

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#0f0f23',
  },
  header: {
    backgroundColor: 'transparent',
    paddingTop: 50,
    paddingBottom: 30,
    paddingHorizontal: 20,
  },
  gradientBackground: {
    position: 'absolute',
    left: 0,
    right: 0,
    top: 0,
    height: height * 0.3,
    borderBottomLeftRadius: 30,
    borderBottomRightRadius: 30,
  },
  userInfo: {
    flexDirection: 'row',
    alignItems: 'center',
    marginBottom: 20,
  },
  avatar: {
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: 'rgba(255, 255, 255, 0.9)',
    justifyContent: 'center',
    alignItems: 'center',
    shadowColor: '#6200ee',
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.3,
    shadowRadius: 8,
    elevation: 8,
  },
  avatarText: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#6200ee',
  },
  userDetails: {
    flex: 1,
    marginLeft: 15,
  },
  userName: {
    fontSize: 24,
    fontWeight: 'bold',
    color: '#ffffff',
  },
  userLevel: {
    fontSize: 16,
    color: 'rgba(255, 255, 255, 0.8)',
    marginTop: 2,
  },
  xpContainer: {
    backgroundColor: 'rgba(255, 255, 255, 0.15)',
    paddingHorizontal: 12,
    paddingVertical: 6,
    borderRadius: 20,
    alignSelf: 'flex-start',
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.2)',
  },
  xpText: {
    fontSize: 12,
    fontWeight: 'bold',
    color: '#ffffff',
  },
  streakBadge: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: 'rgba(255, 215, 0, 0.2)',
    paddingHorizontal: 16,
    paddingVertical: 10,
    borderRadius: 25,
    borderWidth: 1,
    borderColor: '#FFD700',
  },
  streakText: {
    fontSize: 14,
    fontWeight: '600',
    color: '#FFD700',
    marginLeft: 8,
  },
  metricsGrid: {
    flexDirection: 'row',
    flexWrap: 'wrap',
    paddingHorizontal: 20,
    marginTop: -30,
    marginBottom: 25,
    justifyContent: 'space-between',
    alignItems: 'flex-start',
  },
  metricCard: {
    width: (width - 60) / 2,
    height: 140,
    backgroundColor: '#ffffff',
    borderRadius: 24,
    marginBottom: 10,
    elevation: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.2,
    shadowRadius: 12,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.1)',
  },
  metricGradient: {
    flex: 1,
    padding: 20,
    alignItems: 'center',
    justifyContent: 'center',
  },
  metricIcon: {
    width: 50,
    height: 50,
    borderRadius: 25,
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 10,
  },
  metricValue: {
    fontSize: 28,
    fontWeight: 'bold',
    color: '#ffffff',
    marginBottom: 5,
  },
  metricLabel: {
    fontSize: 14,
    fontWeight: '600',
    color: 'rgba(255, 255, 255, 0.9)',
    textAlign: 'center',
  },
  workoutCard: {
    margin: 15,
    marginTop: 5,
    backgroundColor: '#ffffff',
    borderRadius: 24,
    elevation: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.2,
    shadowRadius: 12,
    overflow: 'hidden',
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.1)',
  },
  workoutGradient: {
    padding: 20,
  },
  cardTitleContainer: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 20,
  },
  cardTitle: {
    fontSize: 22,
    fontWeight: 'bold',
    color: '#ffffff',
  },
  workoutList: {
    marginTop: 10,
  },
  workoutItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 15,
    paddingHorizontal: 20,
    backgroundColor: 'rgba(255, 255, 255, 0.1)',
    borderRadius: 15,
    marginBottom: 10,
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.1)',
  },
  workoutIcon: {
    width: 40,
    height: 40,
    borderRadius: 20,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 15,
  },
  workoutInfo: {
    flex: 1,
  },
  workoutName: {
    fontSize: 16,
    fontWeight: '600',
    color: '#ffffff',
  },
  workoutDetails: {
    fontSize: 14,
    color: 'rgba(255, 255, 255, 0.7)',
    marginTop: 2,
  },
  workoutProgress: {
    width: 60,
    height: 60,
    justifyContent: 'center',
    alignItems: 'center',
  },
  progressRing: {
    width: 50,
    height: 50,
    borderRadius: 25,
    borderWidth: 3,
    borderColor: 'rgba(255, 255, 255, 0.3)',
    justifyContent: 'center',
    alignItems: 'center',
  },
  progressRingCompleted: {
    borderColor: '#4CAF50',
  },
  progressText: {
    fontSize: 12,
    fontWeight: 'bold',
    color: '#ffffff',
  },
  startButton: {
    backgroundColor: 'rgba(255, 255, 255, 0.2)',
    borderRadius: 15,
    marginTop: 20,
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.3)',
  },
  startButtonText: {
    fontSize: 16,
    fontWeight: '600',
    color: '#ffffff',
  },
  progressCard: {
    margin: 15,
    marginTop: 5,
    backgroundColor: '#ffffff',
    borderRadius: 24,
    elevation: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.2,
    shadowRadius: 12,
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.1)',
    overflow: 'hidden',
  },
  progressGradient: {
    padding: 20,
  },
  progressHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    marginBottom: 25,
  },
  progressTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#333',
    marginLeft: 12,
  },
  weeklyStats: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    marginBottom: 25,
  },
  dayContainer: {
    alignItems: 'center',
    flex: 1,
  },
  dayCircle: {
    width: 60,
    height: 60,
    borderRadius: 30,
    backgroundColor: '#f0f0f0',
    justifyContent: 'flex-end',
    alignItems: 'center',
    marginBottom: 8,
    position: 'relative',
    overflow: 'hidden',
  },
  dayProgress: {
    width: '100%',
    backgroundColor: '#4CAF50',
    position: 'absolute',
    bottom: 0,
    left: 0,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
  },
  dayProgressText: {
    fontSize: 10,
    fontWeight: 'bold',
    color: '#ffffff',
    position: 'absolute',
    bottom: 8,
  },
  dayName: {
    fontSize: 12,
    fontWeight: '600',
    color: '#666',
    marginBottom: 4,
  },
  progressSummary: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    backgroundColor: 'rgba(98, 0, 238, 0.05)',
    borderRadius: 16,
    padding: 16,
    marginTop: 10,
  },
  summaryItem: {
    alignItems: 'center',
    flex: 1,
  },
  summaryValue: {
    fontSize: 18,
    fontWeight: 'bold',
    color: '#6200ee',
  },
  summaryLabel: {
    fontSize: 11,
    color: '#666',
    marginTop: 2,
    textAlign: 'center',
  },
  activitiesCard: {
    margin: 15,
    marginTop: 5,
    backgroundColor: '#ffffff',
    borderRadius: 24,
    elevation: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 6 },
    shadowOpacity: 0.2,
    shadowRadius: 12,
    borderWidth: 1,
    borderColor: 'rgba(255, 255, 255, 0.1)',
    overflow: 'hidden',
  },
  activitiesHeader: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    padding: 20,
    paddingBottom: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#f0f0f0',
  },
  activitiesTitle: {
    fontSize: 20,
    fontWeight: 'bold',
    color: '#333',
  },
  activitiesList: {
    padding: 10,
  },
  activityItem: {
    flexDirection: 'row',
    alignItems: 'center',
    paddingVertical: 15,
    borderBottomWidth: 1,
    borderBottomColor: '#f0f0f0',
  },
  activityLeft: {
    flexDirection: 'row',
    alignItems: 'center',
    flex: 1,
  },
  activityIcon: {
    width: 45,
    height: 45,
    borderRadius: 22.5,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 15,
  },
  activityInfo: {
    flex: 2,
    justifyContent: 'center',
  },
  activityTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#333',
    marginBottom: 2,
  },
  activitySubtitle: {
    fontSize: 14,
    color: '#666',
    marginBottom: 2,
  },
  activityTime: {
    fontSize: 12,
    color: '#999',
  },
  activityArrow: {
    marginLeft: 10,
  },
  activityCard: {
    backgroundColor: '#f8f9fa',
    borderRadius: 16,
    margin: 5,
    padding: 16,
    elevation: 4,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    borderWidth: 1,
    borderColor: 'rgba(0, 0, 0, 0.05)',
  },
  activityCardGradient: {
    flexDirection: 'row',
    alignItems: 'center',
  },
  activityIconContainer: {
    width: 50,
    height: 50,
    borderRadius: 25,
    justifyContent: 'center',
    alignItems: 'center',
    marginRight: 16,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 4,
  },
  activityContent: {
    flex: 1,
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  activityTextContainer: {
    flex: 1,
  },
  activityBadge: {
    backgroundColor: '#6200ee',
    borderRadius: 12,
    paddingHorizontal: 8,
    paddingVertical: 4,
    marginBottom: 8,
    alignSelf: 'flex-start',
  },
  activityBadgeText: {
    fontSize: 10,
    fontWeight: 'bold',
    color: '#ffffff',
    textTransform: 'uppercase',
  },
  fab: {
    position: 'absolute',
    margin: 16,
    right: 0,
    bottom: 0,
    backgroundColor: '#6200ee',
  },
});

const DashboardScreen: React.FC = () => {
  const fadeAnim = useRef(new Animated.Value(0)).current;
  const slideAnim = useRef(new Animated.Value(50)).current;
  const scaleAnim = useRef(new Animated.Value(0.8)).current;

  useEffect(() => {
    Animated.parallel([
      Animated.timing(fadeAnim, {
        toValue: 1,
        duration: 800,
        useNativeDriver: true,
      }),
      Animated.timing(slideAnim, {
        toValue: 0,
        duration: 600,
        easing: Easing.out(Easing.exp),
        useNativeDriver: true,
      }),
      Animated.timing(scaleAnim, {
        toValue: 1,
        duration: 500,
        easing: Easing.out(Easing.back(1.5)),
        useNativeDriver: true,
      }),
    ]).start();
  }, []);

  const userStats = {
    level: 12,
    xp: 2450,
    streak: 7,
    totalWorkouts: 48,
  };

  const todayStats = {
    calories: 420,
    workoutTime: 45,
    habitsCompleted: 8,
    habitsTotal: 10,
  };

  const weeklyProgress = [
    { day: 'Seg', progress: 100 },
    { day: 'Ter', progress: 80 },
    { day: 'Qua', progress: 0 },
    { day: 'Qui', progress: 65 },
    { day: 'Sex', progress: 90 },
    { day: 'Sáb', progress: 75 },
    { day: 'Dom', progress: 40 },
  ];

  const workoutExercises = [
    { name: 'Supino Reto', sets: '4x8', weight: '60kg', completed: true, icon: 'barbell-outline' },
    { name: 'Desenvolvimento Ombro', sets: '3x12', weight: '12kg', completed: true, icon: 'barbell-outline' },
    { name: 'Tríceps Testa', sets: '3x10', weight: '20kg', completed: false, icon: 'barbell-outline' },
    { name: 'Elevação Lateral', sets: '3x15', weight: '8kg', completed: false, icon: 'barbell-outline' },
  ];

  const recentActivities = [
    {
      id: 1,
      type: 'workout',
      title: 'Treino de Peito',
      subtitle: 'Supino 4x8 • 60kg',
      time: '2h atrás',
      icon: 'fitness',
      color: '#6200ee',
    },
    {
      id: 2,
      type: 'achievement',
      title: 'Novo Recorde',
      subtitle: 'Supino 85kg',
      time: '5h atrás',
      icon: 'trophy',
      color: '#FFD700',
    },
    {
      id: 3,
      type: 'habit',
      title: 'Meta de Água',
      subtitle: '3.5L consumidos',
      time: '1d atrás',
      icon: 'water',
      color: '#4CAF50',
    },
  ];

  return (
    <SafeAreaView style={styles.container}>
      <StatusBar barStyle="light-content" backgroundColor="#0f0f23" />

      {/* Animated Background Gradient */}
      <LinearGradient
        colors={['#6200ee', '#9c27b0', '#ba68c8']}
        start={{ x: 0, y: 0 }}
        end={{ x: 1, y: 1 }}
        style={styles.gradientBackground}
      />

      <Animated.ScrollView
        showsVerticalScrollIndicator={false}
        style={[
          { opacity: fadeAnim },
          { transform: [{ translateY: slideAnim }] },
        ]}
      >
        {/* Header */}
        <View style={styles.header}>
          <View style={styles.userInfo}>
            <Animated.View style={[{ transform: [{ scale: scaleAnim }] }]}>
              <View style={styles.avatar}>
                <Text style={styles.avatarText}>JS</Text>
              </View>
            </Animated.View>
            <View style={styles.userDetails}>
              <Text style={styles.userName}>João Silva</Text>
              <Text style={styles.userLevel}>Nível {userStats.level}</Text>
              <View style={styles.xpContainer}>
                <Text style={styles.xpText}>{userStats.xp} XP</Text>
              </View>
            </View>
            <View style={styles.streakBadge}>
              <Ionicons name="flame" size={20} color="#FFD700" />
              <Text style={styles.streakText}>{userStats.streak} dias</Text>
            </View>
          </View>
        </View>

        {/* Metrics Grid */}
        <View style={styles.metricsGrid}>
          <Animated.View style={[{ transform: [{ scale: scaleAnim }] }]}>
            <TouchableOpacity style={styles.metricCard} activeOpacity={0.8}>
              <LinearGradient
                colors={['#4CAF50', '#45a049']}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 1 }}
                style={styles.metricGradient}
              >
                <View style={[styles.metricIcon, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
                  <Ionicons name="fitness" size={24} color="#ffffff" />
                </View>
                <Text style={styles.metricValue}>{todayStats.calories}</Text>
                <Text style={styles.metricLabel}>CALORIAS</Text>
              </LinearGradient>
            </TouchableOpacity>
          </Animated.View>

          <Animated.View style={[{ transform: [{ scale: scaleAnim }] }]}>
            <TouchableOpacity style={styles.metricCard} activeOpacity={0.8}>
              <LinearGradient
                colors={['#FF9800', '#f57c00']}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 1 }}
                style={styles.metricGradient}
              >
                <View style={[styles.metricIcon, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
                  <Ionicons name="time" size={24} color="#ffffff" />
                </View>
                <Text style={styles.metricValue}>{todayStats.workoutTime}</Text>
                <Text style={styles.metricLabel}>MINUTOS</Text>
              </LinearGradient>
            </TouchableOpacity>
          </Animated.View>

          <Animated.View style={[{ transform: [{ scale: scaleAnim }] }]}>
            <TouchableOpacity style={styles.metricCard} activeOpacity={0.8}>
              <LinearGradient
                colors={['#2196F3', '#1976d2']}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 1 }}
                style={styles.metricGradient}
              >
                <View style={[styles.metricIcon, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
                  <Ionicons name="checkmark-circle" size={24} color="#ffffff" />
                </View>
                <Text style={styles.metricValue}>{todayStats.habitsCompleted}/{todayStats.habitsTotal}</Text>
                <Text style={styles.metricLabel}>HÁBITOS</Text>
              </LinearGradient>
            </TouchableOpacity>
          </Animated.View>

          <Animated.View style={[{ transform: [{ scale: scaleAnim }] }]}>
            <TouchableOpacity style={styles.metricCard} activeOpacity={0.8}>
              <LinearGradient
                colors={['#9C27B0', '#7b1fa2']}
                start={{ x: 0, y: 0 }}
                end={{ x: 1, y: 1 }}
                style={styles.metricGradient}
              >
                <View style={[styles.metricIcon, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
                  <Ionicons name="stats-chart" size={24} color="#ffffff" />
                </View>
                <Text style={styles.metricValue}>{userStats.totalWorkouts}</Text>
                <Text style={styles.metricLabel}>TREINOS</Text>
              </LinearGradient>
            </TouchableOpacity>
          </Animated.View>
        </View>

        {/* Today's Workout */}
        <Card style={styles.workoutCard}>
          <LinearGradient
            colors={['#6200ee', '#9c27b0']}
            start={{ x: 0, y: 0 }}
            end={{ x: 1, y: 1 }}
            style={styles.workoutGradient}
          >
            <View style={styles.cardTitleContainer}>
              <View style={{ flexDirection: 'row', alignItems: 'center' }}>
                <Ionicons name="fitness" size={28} color="#ffffff" />
                <Text style={styles.cardTitle}>Treino de Hoje</Text>
              </View>
              <TouchableOpacity style={{ padding: 5 }}>
                <Ionicons name="ellipsis-horizontal" size={24} color="#ffffff" />
              </TouchableOpacity>
            </View>

            <View style={styles.workoutList}>
              {workoutExercises.map((exercise, index) => (
                <View key={index} style={styles.workoutItem}>
                  <View style={[styles.workoutIcon, { backgroundColor: 'rgba(255, 255, 255, 0.2)' }]}>
                    <Ionicons name={exercise.icon as any} size={20} color="#ffffff" />
                  </View>
                  <View style={styles.workoutInfo}>
                    <Text style={styles.workoutName}>{exercise.name}</Text>
                    <Text style={styles.workoutDetails}>{exercise.sets} • {exercise.weight}</Text>
                  </View>
                  <View style={styles.workoutProgress}>
                    <View style={[styles.progressRing, exercise.completed && styles.progressRingCompleted]}>
                      <Ionicons
                        name={exercise.completed ? "checkmark" : "ellipse"}
                        size={16}
                        color="#ffffff"
                      />
                    </View>
                  </View>
                </View>
              ))}
            </View>

            <Button
              mode="contained"
              style={styles.startButton}
              labelStyle={styles.startButtonText}
              onPress={() => {}}
            >
              Iniciar Treino
            </Button>
          </LinearGradient>
        </Card>

        {/* Bottom padding for FAB */}
        <View style={{ height: 80 }} />
      </Animated.ScrollView>

      {/* FAB */}
      <FAB
        style={styles.fab}
        icon="plus"
        onPress={() => {}}
      />
    </SafeAreaView>
  );
};

export default DashboardScreen;
