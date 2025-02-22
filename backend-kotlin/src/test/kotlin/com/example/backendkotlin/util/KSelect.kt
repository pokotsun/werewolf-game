package com.example.backendkotlin.util

import org.instancio.Select
import org.instancio.TargetSelector
import kotlin.reflect.KProperty1
import kotlin.reflect.jvm.javaField

/**
 * Instancio で用意したダミークラスにプロパティ値を挿入したい時に、公式ドキュメントで推奨されている `field` 関数を直接使う方法だと、
 * property にアクセスする手段がなくエラーが発生するため、KSelect という拡張関数を用意
 */
object KSelect {
    fun <T, V> field(property: KProperty1<T, V>): TargetSelector {
        val field = property.javaField!!
        return Select.field(field.declaringClass, field.name)
    }
}
