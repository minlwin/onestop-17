'use client'

import FormsInput from "@/components/forms/forms-input"
import FormsTextarea from "@/components/forms/forms-textarea"
import { Button } from "@/components/ui/button"
import { SubscriptionPlan, SubscriptionPlanForm, subscriptionPlanSchema } from "@/lib/types"
import { executeAction } from "@/lib/utils"
import { zodResolver } from "@hookform/resolvers/zod"
import { Plus, Save, Trash } from "lucide-react"
import { useFieldArray, useForm } from "react-hook-form"
import * as action from "@/lib/service/action/management/subscription-plan.action"
import { Card, CardContent } from "@/components/ui/card"

export default function PlanEditComponent({plan} : {plan? : SubscriptionPlan}) {
    
    const form = useForm({
        resolver : zodResolver(subscriptionPlanSchema),
        defaultValues: {
            name : plan?.name || '',
            description : plan?.description || '',
            price : plan?.price || 0,
            maxEmployee : plan?.maxEmployee || 0,
            maxLedger : plan?.maxLedger || 0,
            maxEntry : plan?.maxEntry || 0,
            features : plan?.features || [
                {name : ""}
            ]
        }
    })

    const featuresArray = useFieldArray({
        control: form.control,
        name : "features",
    })

    function addFeature() {
        featuresArray.append({
            name : ""
        })
    }

    function removeFeature(index : number) {
        featuresArray.remove(index)

        if(form.getValues('features').length === 0) {
            addFeature()
        }
    }

    function save(form: SubscriptionPlanForm) {
        executeAction(async () => {
            if(plan) {
                action.update(plan.id, form)
            } else {
                action.create(form)
            }
        })
    }
    
    return (
        <form onSubmit={form.handleSubmit(save)} className="space-y-6">
            <Card>
                <CardContent>
                    <section className="space-y-4">
                        <h3 className="text-xl font-semibold text-gray-600">Plan Setting</h3>

                        <div className="grid grid-cols-3 gap-4">
                            {/* Basic Inputs */}
                            <FormsInput control={form.control} name="name" label="Plan Name" />
                            <FormsInput control={form.control} name="price" label="Monthly Fee" type="number" />
                            <FormsInput control={form.control} name="maxEmployee" label="Employee Limit" type="number" className="col-start-1" />
                            <FormsInput control={form.control} name="maxLedger" label="Ledger Limit" type="number" />
                            <FormsInput control={form.control} name="maxEntry" label="Daily Entry Limit" type="number" />
                        </div>
                    </section>

                </CardContent>
            </Card>

            <Card>
                <CardContent>
                    <section className="space-y-4">
                        {/* Features */}
                        <nav className="flex justify-between">
                            <h3 className="text-xl font-semibold text-gray-600">Plan Features</h3>
                            <Button onClick={addFeature} variant={'outline'}>
                                <Plus /> Add Feature
                            </Button>
                        </nav>

                        <div className="space-y-2">
                            {featuresArray.fields.map((item, index) => 
                                <div key={item.id} className="flex gap-1">
                                    <FormsInput control={form.control} name={`features.${index}.name`} />
                                    <Button variant={'outline'} onClick={() => removeFeature(index)}>
                                        <Trash />
                                    </Button>
                                </div>
                            )}
                        </div>
                    </section>                    
                </CardContent>
            </Card>
            
            <Card>
                <CardContent>
                    <section className="space-y-4">
                        <h3 className="text-xl font-semibold text-gray-600">Description</h3>
                        <FormsTextarea control={form.control} name="description" />
                    </section>
                </CardContent>
            </Card>

            <Button type="submit">
                <Save /> Save Plan
            </Button>
        </form>
    )
}