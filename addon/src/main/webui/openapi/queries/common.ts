// generated with @7nohe/openapi-react-query-codegen@2.0.0-beta.3 

import { type Options } from "@hey-api/client-fetch";
import { UseQueryResult } from "@tanstack/react-query";
import { getApiOverview, postApiOverviewCheckByIdDisable, postApiOverviewCheckByIdEnable, postApiOverviewGenerate } from "../requests/services.gen";
export type GetApiOverviewDefaultResponse = Awaited<ReturnType<typeof getApiOverview>>["data"];
export type GetApiOverviewQueryResult<TData = GetApiOverviewDefaultResponse, TError = unknown> = UseQueryResult<TData, TError>;
export const useGetApiOverviewKey = "GetApiOverview";
export const UseGetApiOverviewKeyFn = (clientOptions: Options<unknown, true> = {}, queryKey?: Array<unknown>) => [useGetApiOverviewKey, ...(queryKey ?? [clientOptions])];
export type PostApiOverviewCheckByIdDisableMutationResult = Awaited<ReturnType<typeof postApiOverviewCheckByIdDisable>>;
export const usePostApiOverviewCheckByIdDisableKey = "PostApiOverviewCheckByIdDisable";
export const UsePostApiOverviewCheckByIdDisableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiOverviewCheckByIdDisableKey, ...(mutationKey ?? [])];
export type PostApiOverviewCheckByIdEnableMutationResult = Awaited<ReturnType<typeof postApiOverviewCheckByIdEnable>>;
export const usePostApiOverviewCheckByIdEnableKey = "PostApiOverviewCheckByIdEnable";
export const UsePostApiOverviewCheckByIdEnableKeyFn = (mutationKey?: Array<unknown>) => [usePostApiOverviewCheckByIdEnableKey, ...(mutationKey ?? [])];
export type PostApiOverviewGenerateMutationResult = Awaited<ReturnType<typeof postApiOverviewGenerate>>;
export const usePostApiOverviewGenerateKey = "PostApiOverviewGenerate";
export const UsePostApiOverviewGenerateKeyFn = (mutationKey?: Array<unknown>) => [usePostApiOverviewGenerateKey, ...(mutationKey ?? [])];
